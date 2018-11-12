package pl.ccki.plugins

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestedExtension
import org.gradle.api.Project
import pl.ccki.plugins.base.BaseAndroidPlugin

open class PresentationPlugin : BaseAndroidPlugin() {

    override fun apply(project: Project) {
        project.plugins.apply("com.android.library")
        super.apply(project)

        project.extensions.getByType(LibraryExtension::class.java).apply {
            testOptions {
                it.unitTests.isIncludeAndroidResources = true
            }
            libraryVariants.all {
                it.generateBuildConfigProvider.configure { it.enabled = false }
            }
        }

        project.dependencies.add("implementation","androidx.core:core-ktx:1.0.0")
        project.dependencies.add("testImplementation", "org.robolectric:robolectric:4.0.1")
        project.dependencies.add("testImplementation", "androidx.test:core:1.0.0")
    }
}
