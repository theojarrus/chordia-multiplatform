plugins {
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":chordia-rendering-api"))
                api(project(":chordia-rendering-configuration"))
                api(project(":chordia-svg-configuration"))

                implementation(project(":chordia-configuration-base"))
                implementation(project(":chordia-rendering-base"))
                implementation(project(":chordia-svg-base"))
            }
        }
    }
}
