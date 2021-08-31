plugins {
    id("com.android.application")
    id("com.build.plugin")
}
android { defaultConfig { applicationId = "com.flywith24.demo_paging" } }

dependencies {
    implementation(project(":library_base"))
    implementation(AndroidX.Paging.pagingRuntimeKtx)
    implementation(AndroidX.Room.roomRuntime)
    implementation(AndroidX.Room.roomKtx)
    kapt(AndroidX.Room.roomCompiler)

    implementation(ThirdParty.retrofit)
    implementation(ThirdParty.converterGson)
}