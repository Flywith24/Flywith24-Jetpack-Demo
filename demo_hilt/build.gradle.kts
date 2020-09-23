plugins {
    id("com.android.application")
    id("com.build.plugin")
    id("dagger.hilt.android.plugin")
}
android {
    defaultConfig { applicationId = "com.flywith24.demo_hilt" }
}

dependencies {
    implementation(project(":library_base"))
    implementation(Google.hilt)
    kapt(Google.hiltCompiler)
    implementation(ThirdParty.retrofit)
    implementation(ThirdParty.converterGson)
}