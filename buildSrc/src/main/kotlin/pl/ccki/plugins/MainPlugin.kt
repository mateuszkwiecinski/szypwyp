package pl.ccki.plugins

import org.gradle.api.Project
import org.gradle.api.artifacts.ModuleDependency
import pl.ccki.plugins.base.BaseAndroidPlugin

open class MainPlugin : BaseAndroidPlugin() {

    override fun apply(project: Project) {
        project.plugins.apply("com.android.application")
        super.apply(project)
        project.dependencies.add("implementation", "com.jakewharton.timber:timber-android:5.0.0-SNAPSHOT")
    }
}
