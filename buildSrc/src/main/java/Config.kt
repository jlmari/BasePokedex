object Config {
    object AppConfig {
        const val appNamespace = "com.jlmari.android.basepokedex"
        const val memoryNamespace = "com.jlmari.android.basepokedex.memorydatasource"
        const val appId = "com.jlmari.android.basepokedex"
        const val compileSdkVersion = 33
        const val targetSdkVersion = 33
        const val minSdkVersion = 21
        const val versionCode = 1
        const val versionName = "1"
        const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    object Dependencies {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.xNNavigation}"
    }

    object Plugins {
        const val androidApplication = "com.android.application"
        const val kotlin = "kotlin"
        const val kotlinAndroid = "kotlin-android"
        const val kotlinKapt = "kotlin-kapt"
        const val navigationSafeArgs = "androidx.navigation.safeargs"
        const val androidLibrary = "com.android.library"
        const val javaLibrary = "java-library"
    }
}
