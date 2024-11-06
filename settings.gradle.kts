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
include(":core:common-domain")
include(":core:network")
include(":core:database")
include(":feature:products-data")
include(":core:models")
include(":core:common-test")
include(":feature:products-list")
include(":feature:product-details")
include(":core:common-design-android")
