plugins {
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.composeMultiplatform).apply(false)
}

buildscript {
    dependencies {
        classpath(libs.android.plugin)
    }
}
