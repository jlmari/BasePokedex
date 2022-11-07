object Libraries {

    // Support
    const val xAppCompat = "androidx.appcompat:appcompat:${Versions.xAppcompat}"
    const val xCoreKtx = "androidx.core:core-ktx:${Versions.xCoreKtx}"
    const val xMultidex = "androidx.multidex:multidex:${Versions.xMultidex}"

    // Kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    // Arch Components
    const val xLifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.xLifecycle}"
    const val xLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.xLifecycle}"

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
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    // Room
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // Dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    // Testing
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val mockK = "io.mockk:mockk:${Versions.mockK}"
    const val mockKAndroid = "io.mockk:mockk-android:${Versions.mockK}"
    const val robolectric =  "org.robolectric:robolectric:${Versions.robolectric}"
}
