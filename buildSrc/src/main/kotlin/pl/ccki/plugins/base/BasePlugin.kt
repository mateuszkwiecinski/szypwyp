package pl.ccki.plugins.base

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jmailen.gradle.kotlinter.KotlinterExtension

open class BasePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.repositories.google()
        project.repositories.jcenter()

        project.pluginManager.apply("org.jmailen.kotlinter")
        project.extensions.configure(KotlinterExtension::class.java) {
            it.indentSize = 4
            it.continuationIndentSize = 4
        }
        project.tasks.withType(KotlinCompile::class.java).all {
            it.kotlinOptions.freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
        }
    }
}
