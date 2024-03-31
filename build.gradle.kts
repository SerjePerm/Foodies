// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    //Standard alias
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    //KSP
    id("com.google.devtools.ksp") version "1.9.21-1.0.15" apply false
    //DI Hilt
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
}