plugins {
    id("com.android.application")
    id("com.build.plugin")
}
android { defaultConfig { applicationId = "com.flywith24.fragment.backstack" } }

dependencies {
    implementation(project(":library_base"))
}