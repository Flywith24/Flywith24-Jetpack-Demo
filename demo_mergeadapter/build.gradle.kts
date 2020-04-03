import com.flywith24.buildsrc.dependencies.AndroidX

plugins {
    id("com.android.application")
    id("com.build.plugin")
}
android { defaultConfig { applicationId = "com.flywith24.demo_mergeadapter" } }

dependencies {
    implementation(project(":library_base"))
    implementation(AndroidX.recyclerView)
}