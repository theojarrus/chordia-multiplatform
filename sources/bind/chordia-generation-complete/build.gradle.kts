plugins {
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":chordia-cache-api"))
                api(project(":chordia-generation-essential"))

                implementation(project(":chordia-configuration-base"))
                implementation(project(":chordia-generation-base"))
                implementation(project(":chordia-generation-cache"))
            }
        }
    }
}
