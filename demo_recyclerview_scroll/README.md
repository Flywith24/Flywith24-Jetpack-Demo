## 被我忽视的更新

`androidx recyclerview 1.2.0-alpha02` 版本添加了新功能 [MergeAdapter](https://developer.android.com/reference/androidx/recyclerview/widget/MergeAdapter)，帮助开发者更容易地为 RecyclerView 添加 Header 和 Footer。详情参见 [【译】MergeAdapter 的使用 使用官方 API 为 Recyclerview 添加 Header 和 Footer](https://juejin.im/post/5e86ffea51882573ba207a19)

该版本中还有一个改动：**`RecyclerView.Adapter` lazy state restoration**，帮助开发者恢复 RecyclerView 的状态

![recyclerview update](https://gitee.com/flywith24/Album/raw/master/img/20200512105548.png)



我对这个功能并没有什么感觉。众所周知，Android 中的 View 内部是有着状态保存和恢复的方法的。RecyclerView 也是如此，它可以恢复自身已滚动的位置

![View 内部恢复状态](https://gitee.com/flywith24/Album/raw/master/img/20200512110411.png)

有关状态保存的内容可以参见 [【背上Jetpack】绝不丢失的状态 androidx SaveState ViewModel-SaveState 分析](https://juejin.im/post/5e738d12518825495d69cfb9)



真实情况也是如此

![RecyclerView 内部可以恢复滚动位置](https://gitee.com/flywith24/Album/raw/master/img/20200512111315.gif)



## 意外发现

最近看到 [Florina Muntenescu](https://medium.com/@florina.muntenescu?source=post_page-----a8fbdc9a9334----------------------) 的 [Restore RecyclerView scroll position](https://medium.com/androiddevelopers/restore-recyclerview-scroll-position-a8fbdc9a9334) ，其中介绍了 **`RecyclerView.Adapter` lazy state restoration**，这勾起了我的兴趣

![意外发现](https://gitee.com/flywith24/Album/raw/master/img/20200512111720.png)

如文中描述，RecyclerView 在 activity/fragment 重建时失去滚动位置是因为 Adapter 中的数据是 **异步** 加载的，当 RecyclerView layout 时数据并没有加载，因此也恢复不了之前的位置状态。一个比较简单的例子是使用 Navigation 组件进行导航，返回时 fragment 中的 RecyclerView 由于再次调用接口获取数据，导致其滑动位置失去

![延迟加载数据，无法恢复滚动位置](https://gitee.com/flywith24/Album/raw/master/img/20200512113141.gif)



## 解决方案

有几种方法可以保证 RecyclerView 恢复到正确的滚动位置，最好的办法是借助缓存，ViewModel 或 Repository 中缓存要显示的数据，确保始终在第一个布局传入前在 Adapter 上设置数据。也有一些其他的方案，这些方案要么太复杂，要么不够优雅



`recyclerview:1.2.0-alpha02` 中的解决方案是提供一个新的 Adapter 方法，该方法允许设置状态恢复策略，它有三个选项

- [ALLOW](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter.StateRestorationPolicy#ALLOW)
- [PREVENT_WHEN_EMPTY](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter.StateRestorationPolicy#PREVENT_WHEN_EMPTY)
- [PREVENT](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter.StateRestorationPolicy#PREVENT)



### ALLOW

这是 **默认** 的状态，它会立即恢复 RecyclerView 的状态，该种策略无法解决延迟加载的数据的问题，可以使用 `PREVENT_WHEN_EMPTY`



### PREVENT_WHEN_EMPTY

仅当 Adapter 不为空（adapter.getItemCount() > 0）时，才恢复 RecyclerView 状态。 如果您的数据是异步加载的，那么 RecyclerView 会一直等到数据加载完毕，然后状态才能恢复。 如果您有默认 item（例如 Header 或 加载指示器）作为适配器的一部分，则应该使用`PREVENT` 选项，除非使用 MergeAdapter 添加了默认 item。 MergeAdapter 等待所有适配器准备就绪，然后才恢复状态



### PREVENT

状态不会恢复，直到配置了 `ALLOW` 或者 `PREVENT_WHEN_EMPTY`



使用方式如下：

```  kotlin
adapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY
```



**加入了上面的配置后即使是异步加载数据也能恢复 RecyclerView 的位置**



![设置 PREVENT_WHEN_EMPTY](https://gitee.com/flywith24/Album/raw/master/img/20200512114332.gif)



## 追踪引入过程

老规矩，我们沿着官方的 commit log 来看看其实现原理

首先我们看看 [IssueTracker 上提的 Feature](https://issuetracker.google.com/issues/146365793)

![IssueTracker](https://gitee.com/flywith24/Album/raw/master/img/20200512115048.png)

表达的意思也很简单，就是当加载异步数据时 RecyclerView 的位置状态无法恢复，Adapter 应该提供相关的解决方案



有意思的是，实现该功能时还重新实现了前一个版本的逻辑，我在 git commit log 中看到了 revert 操作

![revert操作](https://gitee.com/flywith24/Album/raw/master/img/20200512140140.png)

![](https://gitee.com/flywith24/Album/raw/master/img/20200512120004.png)



为了防止 `LayoutManager#onRestore` 执行多次，没有采用最开始的实现方式。但 Yigit Boyar (这个提交的开发者) 仍然希望使用最开始的实现方式，但是  `LayoutManager#onRestoreInstance` 的状态时 public ，因此只能选取一个折中的方案



![新的实现方案](https://gitee.com/flywith24/Album/raw/master/img/20200512141210.png)

![无奈之举](https://gitee.com/flywith24/Album/raw/master/img/20200512141445.png)



过去，开发者会无意间调用 `onRestoreInstanceState(State)` 方法。例如，一些开发者已使用它来手动设置自己更新的状态，这样即使在此状态之前已恢复，在此处传递状态也将导致 LayoutManager 接收它并相应地更新其内部状态。因此，即使看起来好像很奇怪，也必须始终调用 `requestLayout` 来保留功能



## 源码分析

接下来我们来分析这部分源码，内容很少，所以我们详细看下

首先是引入 `StateRestorationPolicy `的枚举

![](https://gitee.com/flywith24/Album/raw/master/img/20200512143600.png)

然后需要提供 `setStateRestorationPolicy` 和 `getStateRestorationPolicy` 方法，此时我们还需要一个方法来判断是否要将 SavedState 传递给 LayoutManager

![](https://gitee.com/flywith24/Album/raw/master/img/20200512143459.png)



前面的 `setStateRestorationPolicy` 方法中 调用了 `notifyStateRestorationPolicyChanged`，而 `notifyStateRestorationPolicyChanged` 为静态类 `AdapterDataObservable` 中的方法，该类中的其他方法我们也很熟悉，均是刷新 Adapter 中数据的方法。

![notifyStateRestorationPolicyChanged](https://gitee.com/flywith24/Album/raw/master/img/20200512143858.png)



而 `notifyStateRestorationPolicyChanged` 中调用了 mObservers list 中元素的 `onStateRestorationPolicyChanged` 方法，通过源码我们得知该 list 中的元素类型为 `AdapterDataObserver`，因此还需要在 `AdapterDataObserver` 中加入 `onStateRestorationPolicyChanged` 方法

![onStateRestorationPolicyChanged ](https://gitee.com/flywith24/Album/raw/master/img/20200512144501.png)



该方法是个空实现，而 `RecyclerViewDataObserver` 重写了该方法

![RecyclerViewDataObserver ](https://gitee.com/flywith24/Album/raw/master/img/20200512144716.png)



配置恢复策略以及恢复策略变化时的监听都有了，接下来要做的就是如果之前有待恢复的装则恢复之前的状态

![恢复状态](https://gitee.com/flywith24/Album/raw/master/img/20200512145152.png)

> 注意：发布之前 `StateRestorationPolicy` 叫做 `StateRestorationStrategy`，后来命名为 `StateRestorationPolicy`，alpha 版本的库可能随时更改 API 的命名和删除 API，因此查看这部分源码的同学请注意



至此，相关的源码都在这里了



## 总结

`StateRestorationPolicy` 提供了 RecyclerView 异步加载数据恢复滚动位置的解决方案。原理就是通过配置 `StateRestorationPolicy` 来改变恢复策略，同时在策略改变时调用 `requestLayout` 方法。在 `dispatchLayoutStep2()` (该方法会在 onLayout 和 measure 方法中调用) 方法中恢复状态(如果 `canRestoreState()` 返回 true)



**一点思考：我们都知道 ViewPager2 是使用 RecyclerView 实现的，那么借助本文介绍的 API 可以做点什么吗？**

欢迎各位小伙伴在评论区留言，说说你的想法



## 关于我

我是 [Fly_with24](https://flywith24.gitee.io/)
- [掘金](https://juejin.im/user/57c7f6870a2b58006b1cfd6c)

- [简书](https://www.jianshu.com/u/3d5ad6043d66)

- [Github](https://github.com/Flywith24)



![](https://gitee.com/flywith24/Album/raw/master/img/20200508153754.jpg)

