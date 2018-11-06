package pl.ccki.plugins.base

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jmailen.gradle.kotlinter.KotlinterExtension

open class BasePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.repositories.google()
        project.repositories.jcenter()
        project.repositories.maven { it.setUrl("https://oss.sonatype.org/content/repositories/snapshots/") }

        project.pluginManager.apply("org.jmailen.kotlinter")
        project.extensions.configure(KotlinterExtension::class.java) {
            it.indentSize = 4
            it.continuationIndentSize = 4
        }
        project.tasks.withType(KotlinCompile::class.java).all {
            it.kotlinOptions.freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
        }

        project.afterEvaluate {
            it.dependencies.add("testImplementation", "junit:junit:4.12")
            it.dependencies.add("testImplementation", "com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0")
            it.dependencies.add("testImplementation", "org.mockito:mockito-core:2.23.0")
            it.dependencies.add("testImplementation", "org.assertj:assertj-core:3.11.1")
        }
    }
}
