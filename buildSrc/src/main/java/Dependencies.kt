object App {
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29
    const val versionCode = 1
    const val buildTools = "29.0.2"
    const val versionName = "1.0.0"
    const val applicationID = "com.karhoo"
}

object AndroidX {
    const val fragmentKTX = "1.2.0-rc05"
    const val activityKTX = "1.1.0-rc03"
    const val coreKTX = "1.1.0"
    const val appcompatKTX = "1.2.0-alpha01"
    const val swipeFreshLayoutKTX = "1.0.0"
    const val constraintLayoutKTX = "1.1.3"
}

object Versions {
    const val gradle = "3.6.0"
    const val kotlin = "1.3.70"
    const val ktor = "1.3.1"
    const val coroutinesCore = "1.3.3"
    const val serializationCommon = "0.14.0"
    const val cocoapodVersion = "1.8.0"
    const val lifecycleVersion = "2.2.0-rc03"
    const val multiplatformSettingsVersion = "0.5"

    /* TEST */
    const val junit = "4.12"
}

object Web {
    // Web
    const val html = "0.6.9"
    const val kotlinJsExt = "1.0.1-pre.89-kotlin-1.3.60"
    const val react = "16.8.6"
    const val reactDom = react
    const val frontEndPlugin = "0.0.45"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
}