plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
}

android {
    namespace = Config.AppConfig.memoryNamespace
    compileSdk = Config.AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = Config.AppConfig.minSdkVersion
        targetSdk = Config.AppConfig.targetSdkVersion

        testInstrumentationRunner = Config.AppConfig.testRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
}

dependencies {
    // Clean architecture modules
    implementation(project(Modules.domain))
    implementation(project(Modules.data))

    // Kotlin Coroutines
    implementation(Libraries.coroutinesCore)

    // Room
    api(Libraries.room)
    api(Libraries.roomKtx)
    kapt(Libraries.roomCompiler)

    // DI
    implementation(Libraries.dagger)

    // Testing
    testImplementation(Libraries.jUnit)
    testImplementation(Libraries.mockK)
}