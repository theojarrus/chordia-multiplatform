plugins {
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":chordia-generation-api"))
                api(project(":chordia-generation-configuration"))

                implementation(project(":chordia-configuration-base"))
                implementation(project(":chordia-generation-base"))
            }
        }
    }
}
