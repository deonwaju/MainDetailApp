@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kapt)
    alias(libs.plugins.parcelize)
    alias(libs.plugins.ksp)
}

apply {
    from("$rootDir/base-module.gradle")
}

android {
    compileSdk = AndroidCommonConfig.compileSDK

    defaultConfig {
        minSdk = AndroidCommonConfig.minSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = AndroidCommonConfig.javaVersion
        targetCompatibility = AndroidCommonConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = AndroidCommonConfig.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.deontch.feature.products.data"
}

dependencies {
    implementation(project(":core:preferences"))
    implementation(project(":core:database"))
    implementation(project(":core:models"))
    implementation(project(":feature:products:products-domain"))
    implementation(project(":core:network"))

    testImplementation(libs.bundles.testing)
}
