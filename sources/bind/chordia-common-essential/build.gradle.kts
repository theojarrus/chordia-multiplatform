plugins {
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":chordia-common-api"))
                api(project(":chordia-common-configuration"))
                api(project(":chordia-generation-configuration"))
                api(project(":chordia-rendering-configuration"))
                api(project(":chordia-svg-configuration"))

                implementation(project(":chordia-common-base"))
                implementation(project(":chordia-configuration-base"))
                implementation(project(":chordia-generation-essential"))
                implementation(project(":chordia-rendering-essential"))
            }
        }
    }
}
