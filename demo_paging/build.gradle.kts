import com.flywith24.buildsrc.dependencies.AndroidX

plugins {
    id("com.android.application")
    id("com.build.plugin")
}
android { defaultConfig { applicationId = "com.flywith24.demo_paging" } }

dependencies {
    implementation(project(":library_base"))
    implementation(AndroidX.paging)
    implementation("com.squareup.retrofit2:retrofit:2.8.1")
    implementation("com.squareup.retrofit2:converter-gson:2.8.1")
}