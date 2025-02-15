plugins {
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":chordia-rendering-api"))
            }
        }
    }
}
