plugins {
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":chordia-rendering-api"))

                implementation(project(":chordia-rendering-configuration"))
                implementation(project(":chordia-utils"))
            }
        }
    }
}
