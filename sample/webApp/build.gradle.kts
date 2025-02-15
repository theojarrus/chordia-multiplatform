plugins {
    id("convention.multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        jsMain {
            dependencies {
                implementation(compose.html.core)
                implementation(compose.html.svg)
                implementation(compose.runtime)
                implementation(project(":chordia-common-essential"))
            }
        }
    }
}
