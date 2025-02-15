plugins {
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":chordia-common-api"))

                implementation(project(":chordia-common-configuration"))
            }
        }
    }
}
