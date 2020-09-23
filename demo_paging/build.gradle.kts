plugins {
    id("com.android.application")
    id("com.build.plugin")
}
android { defaultConfig { applicationId = "com.flywith24.demo_paging" } }

dependencies {
    implementation(project(":library_base"))
    implementation(AndroidX.paging)
    implementation(AndroidX.Room.roomRuntime)
    implementation(AndroidX.Room.roomKtx)
    add("kapt", AndroidX.Room.roomCompiler)

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}