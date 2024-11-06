pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MainDetailApp"
include(":app")
include(":core:common")
include(":core:common-design-android")
include(":core:common-domain")
include(":core:common-test")
include(":core:database")
include(":core:models")
include(":core:network")
include(":core:preferences")
include(":feature:products:products-data")
include(":feature:products:products-details")
include(":feature:products:products-list")
include(":feature:products:products-domain")
include(":feature:settings")
