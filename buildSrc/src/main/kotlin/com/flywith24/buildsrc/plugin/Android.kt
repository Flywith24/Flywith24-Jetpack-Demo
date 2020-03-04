@file:Suppress("CAST_NEVER_SUCCEEDS")

package com.flywith24.buildsrc.plugin

import com.android.build.gradle.BaseExtension
import com.flywith24.buildsrc.dependencies.Version
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.withType

/**
 * @author yyz(杨云召)
 * @date   2020/1/13
 * time   13:52
 * description
 * android{} 配置
 */

internal fun Project.configureAndroid() = this.extensions.findByType<BaseExtension>()?.run {
    compileSdkVersion(Version.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Version.minSdkVersion)
        targetSdkVersion(Version.targetSdkVersion)
        versionCode = Version.versionCode
        versionName = Version.versionName
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        /*   ndk {
               //'armeabi', 'armeabi-v7a', 'arm64-v8a', 'x86', 'x86_64', 'mips', 'mips64'
               abiFilters("arm64-v8a")
           }*/
    }


    buildTypes {
        getByName("release") {

            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            isMinifyEnabled = false
            isTestCoverageEnabled = true
        }
    }

    packagingOptions {
        exclude("META-INF/NOTICE.txt")
        // ...
    }

    //防止编译的时候oom、GC
    dexOptions { javaMaxHeapSize = "4g" }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dataBinding { isEnabled = true }

    viewBinding { isEnabled = true }


    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

