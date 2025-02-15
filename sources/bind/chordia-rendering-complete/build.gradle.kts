plugins {
    id("convention.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":chordia-cache-api"))
                api(project(":chordia-rendering-essential"))

                implementation(project(":chordia-configuration-base"))
                implementation(project(":chordia-rendering-base"))
                implementation(project(":chordia-rendering-cache"))
                implementation(project(":chordia-svg-base"))
            }
        }
    }
}
