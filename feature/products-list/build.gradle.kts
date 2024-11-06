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
    namespace = "com.deontch.feature.products"

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
    namespace = "com.aliumujib.all.breeds"
}

ksp {
    arg("compose-destinations.mode", "destinations")
    arg("compose-destinations.moduleName", "all.breeds")
}

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

dependencies {
//    implementation(projects.core.designsystem)
    implementation(project(":core:common"))
    implementation(project(":core:common-domain"))
    implementation(project(":core:models"))

    /**
    implementation(projects.core.analytics)
    implementation(projects.core.preferences)
    implementation(projects.feature.products.productsDomain)
    implementation(projects.feature.breeds.common)
    **/
    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.lifecycle.compose.android)

    implementation(libs.compose.destinations.animations)
    ksp(libs.compose.destinations.ksp)

    testImplementation(libs.bundles.testing)
    testImplementation(project(":core:common-test"))
    androidTestImplementation(project(":core:common-test"))
}
