plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    jcenter()
}

dependencies {
    implementation("com.android.tools.build:gradle:3.6.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
    implementation(gradleApi())
    implementation(localGroovy())
}