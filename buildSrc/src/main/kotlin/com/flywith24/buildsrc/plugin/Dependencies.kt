package com.flywith24.buildsrc.plugin

import com.android.build.gradle.api.AndroidBasePlugin
import com.flywith24.buildsrc.dependencies.AndroidX
import com.flywith24.buildsrc.dependencies.Google
import com.flywith24.buildsrc.dependencies.Kotlin
import com.flywith24.buildsrc.dependencies.Testing.androidTestRules
import com.flywith24.buildsrc.dependencies.Testing.androidTestRunner
import com.flywith24.buildsrc.dependencies.Testing.espressoCore
import com.flywith24.buildsrc.dependencies.Testing.jUnit
import org.gradle.api.Project

/**
 * @author yyz (杨云召)
 * @date   2020/1/13
 * time   13:45
 * description
 * 公共依赖
 */

const val implementation = "implementation"
const val debugImplementation = "debugImplementation"
const val testImplementation = "testImplementation"
const val androidTestImplementation = "androidTestImplementation"

internal fun Project.configureDependencies() = dependencies.apply {
    add(implementation, (fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar")))))
    add(testImplementation, jUnit)

    if (project.containsAndroidPlugin()) {
        add(androidTestImplementation, androidTestRunner)
        add(androidTestImplementation, androidTestRules)
        add(androidTestImplementation, espressoCore)
    }

    add(implementation, AndroidX.appcompat)
    add(implementation, AndroidX.coreKtx)
    add(implementation, AndroidX.activityKtx)
    add(implementation, AndroidX.Fragment.fragmentKtx)
    add(debugImplementation, AndroidX.Fragment.fragmentTesting)
    add(implementation, AndroidX.constraintlayout)
    add(implementation, AndroidX.cardview)
    add(implementation, Kotlin.stdlibJdk7)
    add(implementation, Google.material)
    add(implementation, AndroidX.Lifecycle.commonJava8)
    add(implementation, AndroidX.Lifecycle.liveDataKtx)
    add(implementation, AndroidX.Lifecycle.viewModelKtx)

}

internal fun Project.containsAndroidPlugin(): Boolean {
    return project.plugins.toList().any { plugin -> plugin is AndroidBasePlugin }
}