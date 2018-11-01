package pl.ccki.plugins.base

import com.android.build.gradle.TestedExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import pl.ccki.plugins.Versions.DAGGER_VERSION
import pl.ccki.plugins.Versions.KOTLIN_VERSION

abstract class BaseAndroidPlugin : BasePlugin() {

    companion object {
        private const val COMPILE_SDK_VERSION = 28
        private const val MIN_SDK_VERSION = 21
    }

    override fun apply(project: Project) {
        super.apply(project)
        project.repositories.maven { it.setUrl("https://jitpack.io") }

        project.plugins.apply("kotlin-android")
        project.plugins.apply("kotlin-kapt")

        project.extensions.getByType(TestedExtension::class.java).apply {
            compileSdkVersion(COMPILE_SDK_VERSION)
            defaultConfig.minSdkVersion(MIN_SDK_VERSION)
            defaultConfig.targetSdkVersion(COMPILE_SDK_VERSION)
            //defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            sourceSets.getByName("main") {
                it.java.srcDir("src/main/kotlin")
            }
            sourceSets.getByName("test") {
                it.java.srcDir("src/test/kotlin")
            }

            compileOptions.sourceCompatibility = JavaVersion.VERSION_1_8
            compileOptions.targetCompatibility = JavaVersion.VERSION_1_8
        }

        project.dependencies.add("implementation", "org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN_VERSION")

        project.dependencies.add("implementation", "com.google.dagger:dagger:$DAGGER_VERSION")
        project.dependencies.add("implementation", "com.google.dagger:dagger-android:$DAGGER_VERSION")
        project.dependencies.add("implementation", "com.google.dagger:dagger-android-support:$DAGGER_VERSION")
        project.dependencies.add("kapt", "com.google.dagger:dagger-compiler:$DAGGER_VERSION")
        project.dependencies.add("kapt", "com.google.dagger:dagger-android-processor:$DAGGER_VERSION")
    }
}

