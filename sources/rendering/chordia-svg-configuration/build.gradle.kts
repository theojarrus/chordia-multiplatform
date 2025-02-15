plugins {
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":chordia-configuration-api"))
                api(project(":chordia-svg-api"))
            }
        }
    }
}
