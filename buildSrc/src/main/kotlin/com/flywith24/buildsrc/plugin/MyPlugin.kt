package com.flywith24.buildsrc.plugin

import com.flywith24.buildsrc.dependencies.Version
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author yyz (杨云召)
 * @date   2020/3/4
 * time   9:25
 * description
 * 自定义插件
 */
class MyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            //读取gradle.properties中的配置，如果有则覆盖Version中配置的值
            findProperty("VERSION_NAME")?.also {
                Version.versionName = it as String
            }
            findProperty("VERSION_CODE")?.also {
                Version.versionCode = it as Int
            }

            configurePlugins()
            configureAndroid()
            configureDependencies()
        }
    }
}