plugins {
    id("convention.multiplatform")
    kotlin("plugin.serialization") version "2.0.0"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.0")
            }
        }
    }
}
