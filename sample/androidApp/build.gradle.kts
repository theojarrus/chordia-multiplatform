plugins {
    id("convention.android.application")
    id("org.jetbrains.compose")
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.accompanist.drawablepainter)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)

    implementation(compose.ui)
    implementation(compose.material3)
    implementation(compose.material)
    implementation(compose.runtime)
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.bottomSheetNavigator)

    implementation(project(":chordia-common-complete"))
    implementation(project(":chordia-generation-complete"))
    implementation(project(":chordia-rendering-complete"))
}
