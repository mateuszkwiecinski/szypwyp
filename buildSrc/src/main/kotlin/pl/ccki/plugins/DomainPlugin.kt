package pl.ccki.plugins

import org.gradle.api.Project
import pl.ccki.plugins.Versions.JUNIT_VERSION
import pl.ccki.plugins.Versions.KOTLIN_VERSION
import pl.ccki.plugins.Versions.MOCKITO_KOTLIN_VERSION
import pl.ccki.plugins.base.BasePlugin

open class DomainPlugin : BasePlugin() {
    override fun apply(project: Project) {
        super.apply(project)
        project.pluginManager.apply("kotlin")

        project.dependencies.add("implementation", "org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN_VERSION")
        project.dependencies.add("compile", "javax.inject:javax.inject:1")

        project.dependencies.add("testImplementation", "junit:junit:$JUNIT_VERSION")
        project.dependencies.add("testImplementation", "com.nhaarman.mockitokotlin2:mockito-kotlin:$MOCKITO_KOTLIN_VERSION")
        project.dependencies.add("compile", "com.jakewharton.timber:timber-jdk:5.0.0-SNAPSHOT")
    }
}
