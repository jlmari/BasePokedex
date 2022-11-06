plugins {
    id(Config.Plugins.androidApplication)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.navigationSafeArgs)
}

android {
    namespace = Config.AppConfig.namespace
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

    buildTypes {
        release {
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

    // Support
    implementation(Libraries.xAppCompat)
    implementation(Libraries.xCoreKtx)

    // UI
    implementation(Libraries.materialDesign)
    implementation(Libraries.xNavigationFragment)
    implementation(Libraries.xNavigationUi)

    // DI
    implementation(Libraries.dagger)
    kapt(Libraries.daggerCompiler)

    // Testing
    testImplementation(Libraries.jUnit)
    testImplementation(Libraries.mockK)
    androidTestImplementation(Libraries.mockKAndroid)
}
