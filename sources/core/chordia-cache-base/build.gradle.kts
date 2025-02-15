plugins {
    id("convention.multiplatform")
    kotlin("plugin.serialization") version "2.0.0"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":chordia-cache-api"))
            }
        }
    }
}
