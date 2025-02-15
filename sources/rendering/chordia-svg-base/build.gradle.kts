plugins {
    id("convention.multiplatform")
    id("convention.cocoapods")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":chordia-svg-api"))

                implementation(project(":chordia-svg-configuration"))
            }
        }
        androidMain {
            dependencies {
                implementation(libs.android.svg)
            }
        }
        nativeMain {
            cocoapods {
                pod("SVGKit")
            }
        }
        jvmMain {

        }
    }
}
