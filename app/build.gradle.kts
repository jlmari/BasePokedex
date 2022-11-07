plugins {
    id(Config.Plugins.androidApplication)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.navigationSafeArgs)
}

android {
    namespace = Config.AppConfig.appNamespace
    compileSdk = Config.AppConfig.compileSdkVersion

    defaultConfig {
        applicationId = Config.AppConfig.appId
        minSdk = Config.AppConfig.minSdkVersion
        targetSdk = Config.AppConfig.targetSdkVersion
        versionCode = Config.AppConfig.versionCode
        versionName = Config.AppConfig.versionName

        multiDexEnabled = true
        testInstrumentationRunner = Config.AppConfig.testRunner
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            storeFile = file("../certs/debug.keystore")
            storePassword = "android"
        }
        // This is just used for checking proguard rules, use your own release signing key
        create("release") {
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            storeFile = file("../certs/debug.keystore")
            storePassword = "android"
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Clean architecture modules
    implementation(project(Modules.presentation))
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(project(Modules.networkDataSource))
    implementation(project(Modules.memoryDataSource))

    // Support
    implementation(Libraries.xAppCompat)
    implementation(Libraries.xCoreKtx)

    // UI
    implementation(Libraries.materialDesign)
    implementation(Libraries.xNavigationFragment)
    implementation(Libraries.xNavigationUi)
    implementation(Libraries.glide)

    // DI
    implementation(Libraries.dagger)
    kapt(Libraries.daggerCompiler)

    // Testing
    testImplementation(Libraries.jUnit)
    testImplementation(Libraries.mockK)
    androidTestImplementation(Libraries.mockKAndroid)
}
