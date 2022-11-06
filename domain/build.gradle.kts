plugins {
    id(Config.Plugins.javaLibrary)
    id(Config.Plugins.kotlin)
    id(Config.Plugins.kotlinKapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    // Kotlin Coroutines
    implementation(Libraries.coroutinesCore)

    // DI
    implementation(Libraries.dagger)

    // Testing
    testImplementation(Libraries.jUnit)
    testImplementation(Libraries.mockK)
}