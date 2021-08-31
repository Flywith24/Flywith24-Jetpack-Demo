buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {

        classpath(GradlePlugins.ANDROID)
        classpath(kotlin(module = GradlePlugins.Kotlin.ID, version = GradlePlugins.Kotlin.VERSION))
        classpath(Google.hiltPlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()

    }
}

tasks.register<Delete>("clean") {
    delete(buildDir)
}
