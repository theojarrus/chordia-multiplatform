pluginManagement {
    includeBuild("../chordia-build-utils")
    includeBuild("../chordia-build-settings")
}

plugins {
    id("convention.utils")
    id("convention.plugins")
    id("convention.dependencies")
    id("convention.versions")
}
