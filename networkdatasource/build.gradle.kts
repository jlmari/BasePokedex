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
    // Clean architecture modules
    implementation(project(Modules.domain))
    implementation(project(Modules.data))

    // Kotlin Coroutines
    implementation(Libraries.coroutinesCore)

    // Retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitConverter)

    // Okhttp
    implementation(Libraries.okhttp)
    implementation(Libraries.okhttpInterceptor)

    // DI
    implementation(Libraries.dagger)

    // Testing
    testImplementation(Libraries.jUnit)
    testImplementation(Libraries.mockK)
}
