plugins {
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":chordia-configuration-api"))
                api(project(":chordia-generation-api"))
                api(project(":chordia-rendering-api"))
            }
        }
    }
}
