package pl.ccki.plugins

import org.gradle.api.Project
import pl.ccki.plugins.Versions.KOTLIN_VERSION
import pl.ccki.plugins.base.BasePlugin

open class DataPlugin : BasePlugin() {
    override fun apply(project: Project) {
        super.apply(project)
        project.pluginManager.apply("kotlin")

        project.dependencies.add("implementation", "org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN_VERSION")
        project.dependencies.add("implementation", "androidx.annotation:annotation:1.0.0")
    }
}
