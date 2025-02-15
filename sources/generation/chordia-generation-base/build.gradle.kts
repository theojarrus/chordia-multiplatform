plugins {
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":chordia-generation-api"))

                implementation(project(":chordia-generation-configuration"))
                implementation(project(":chordia-utils"))
            }
        }
    }
}
