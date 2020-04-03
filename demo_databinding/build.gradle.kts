plugins {
    id("com.android.application")
    id("com.build.plugin")
}
android { defaultConfig { applicationId = "com.flywith24.demo_databinding" } }

dependencies {
    implementation(project(":library_base"))
}