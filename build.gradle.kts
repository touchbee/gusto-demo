// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.devtools.ksp") version "2.0.0-1.0.21" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

buildscript {

    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.google.com/")
        gradlePluginPortal()
    }

    dependencies {
        classpath (libs.hilt.android.gradle.plugin)
    }
}