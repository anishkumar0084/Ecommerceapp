import org.gradle.kotlin.dsl.maven

pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jcenter.bintray.com")

        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jcenter.bintray.com")


    }
}

rootProject.name = "Ecommerice app"
include(":app")
