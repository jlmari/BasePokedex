object Libraries {

    // Support
    const val xAppCompat = "androidx.appcompat:appcompat:${Versions.xAppcompat}"
    const val xCoreKtx = "androidx.core:core-ktx:${Versions.xCoreKtx}"
    const val xMultidex = "androidx.multidex:multidex:${Versions.xMultidex}"

    // Kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    // Arch Components
    const val xViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.xLifecycle}"
    const val xViewModelState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.xLifecycle}"
    const val xLifeData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.xLifecycle}"
    const val xLifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.xLifecycle}"

    // Kotlin Coroutines
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"

    // Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    // UI
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    const val xNavigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.xNNavigation}"
    const val xNavigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.xNNavigation}"

    // Play Services
    const val playServices = "com.google.android.gms:play-services-auth:${Versions.playServices}"

    // Dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    // Testing
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val mockK = "io.mockk:mockk:${Versions.mockK}"
    const val mockKAndroid = "io.mockk:mockk-android:${Versions.mockK}"
}
