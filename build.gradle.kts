// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {

        classpath(GradlePlugins.ANDROID)
        classpath(kotlin(module = GradlePlugins.Kotlin.ID, version = GradlePlugins.Kotlin.VERSION))
        classpath(Google.hiltPlugin)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()

    }
}

tasks.register<Delete>("clean") {
    delete(buildDir)
}
