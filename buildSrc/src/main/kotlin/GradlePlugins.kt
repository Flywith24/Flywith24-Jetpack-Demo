object GradlePlugins {
    const val ANDROID = "com.android.tools.build:gradle:7.0.1"

    interface GradlePlugin {
        val ID: String
        val VERSION: String
        val APPLY: Boolean
            get() = true
    }

    object Kotlin : GradlePlugin {
        override val ID = "gradle-plugin"
        override val VERSION = "1.5.30"
    }
}
