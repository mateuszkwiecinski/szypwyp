package pl.ccki.plugins

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import pl.ccki.plugins.Versions.DAGGER_VERSION
import pl.ccki.plugins.base.BaseAndroidPlugin

open class DiPlugin : BaseAndroidPlugin() {

    override fun apply(project: Project) {
        project.plugins.apply("com.android.library")
        super.apply(project)
        project.extensions.getByType(LibraryExtension::class.java).apply {
            libraryVariants.all {
                it.generateBuildConfigProvider.configure { it.enabled = false }
            }
        }

        project.dependencies.add("implementation", "com.google.dagger:dagger:$DAGGER_VERSION")
        project.dependencies.add("kapt", "com.google.dagger:dagger-compiler:$DAGGER_VERSION")
    }
}
