// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Config.Dependencies.gradle)
        classpath(Config.Dependencies.kotlin)
        classpath(Config.Dependencies.navigationSafeArgs)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
