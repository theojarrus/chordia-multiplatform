import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("convention.multiplatform")
    id("org.jetbrains.compose")
}

compose {
    desktop {
        application {
            mainClass = "com.neotive.chorida.sample.desktop.ChordiaDesktopSampleAppKt"

            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                packageName = "KotlinJvmComposeDesktopApplication"
                packageVersion = "1.0.0"
            }
        }
    }
}

kotlin {
    sourceSets {
        jvmMain {
            dependencies {
                implementation(project(":chordia-common-base"))
                implementation(compose.desktop.currentOs)
            }
        }
    }
}
