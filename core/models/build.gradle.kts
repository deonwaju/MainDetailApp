@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.parcelize)
}

apply {
    from("$rootDir/base-module.gradle")
}

android {
    namespace = "com.deontch.core.modules"
    compileSdk = AndroidCommonConfig.compileSDK

    defaultConfig {
        minSdk = AndroidCommonConfig.minSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = AndroidCommonConfig.javaVersion
        targetCompatibility = AndroidCommonConfig.javaVersion
    }

    kotlinOptions {
        jvmTarget = AndroidCommonConfig.jvmTarget
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
