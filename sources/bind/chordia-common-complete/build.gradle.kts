plugins {
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":chordia-cache-api"))
                api(project(":chordia-common-essential"))

                implementation(project(":chordia-common-base"))
                implementation(project(":chordia-common-cache"))
                implementation(project(":chordia-configuration-base"))
                implementation(project(":chordia-generation-complete"))
                implementation(project(":chordia-rendering-complete"))
            }
        }
    }
}
