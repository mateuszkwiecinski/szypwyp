package pl.ccki.plugins

import org.gradle.api.Project
import pl.ccki.plugins.base.BaseAndroidPlugin

open class PresentationPlugin : BaseAndroidPlugin() {

    override fun apply(project: Project) {
        project.plugins.apply("com.android.library")
        super.apply(project)
    }
}
