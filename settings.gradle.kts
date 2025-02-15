pluginManagement {
    includeBuild("includedBuild/chordia-build-utils")
    includeBuild("includedBuild/chordia-build-settings")
}

includeBuild("includedBuild/chordia-build-plugins")

includeRecursive("sources")
includeRecursive("sample")

plugins {
    id("convention.utils")
    id("convention.plugins")
    id("convention.dependencies")
}
