package com.flywith24.buildsrc.plugin

import org.gradle.api.Project

/**
 * @author yyz (杨云召)
 * @date   2020/3/4
 * time   9:30
 * description
 * 公共插件
 */
internal fun Project.configurePlugins() {
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-android-extensions")
}