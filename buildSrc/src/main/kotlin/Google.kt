/**
 * @author yyz (杨云召)
 * @date   2020/3/5
 * time   21:09
 * description
 */
object Google {
    private const val hiltVersion = "2.38.1"
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
    const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"

    const val material = "com.google.android.material:material:1.4.0"
    const val gson = "com.google.code.gson:gson:2.8.6"
    private const val protobufVersion = "3.10.0"
    const val protobuf = "com.google.protobuf:protobuf-javalite:$protobufVersion"
    const val protoc = "com.google.protobuf:protoc:$protobufVersion"
}