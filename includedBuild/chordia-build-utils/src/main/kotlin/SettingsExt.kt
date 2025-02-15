import org.gradle.api.initialization.Settings
import java.io.File

fun Settings.includeRecursive(directory: String) {
    File(directory)
        .walkTopDown()
        .maxDepth(2)
        .forEach { subDir ->
            if (isModule(subDir)) {
                val moduleName = ":${subDir.name}"
                include(moduleName)
                project(moduleName).projectDir = subDir
            }
        }
}

private fun isModule(dir: File): Boolean {
    return File(dir, "build.gradle").exists() || File(dir, "build.gradle.kts").exists()
}
