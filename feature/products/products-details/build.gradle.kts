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
    namespace = "com.deontch.feature.product.details"
}

ksp {
    arg("compose-destinations.mode", "destinations")
    arg("compose-destinations.moduleName", "now.playing")
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
    implementation(project(":core:common-design-android"))
    implementation(project(":core:common"))
    implementation(project(":core:preferences"))
    implementation(project(":core:models"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))

    implementation(project(":core:common-domain"))
    implementation(project(":feature:products:products-domain"))

    implementation(libs.androidx.lifecycle.compose.android)

    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(libs.bundles.testing)
    androidTestImplementation(project(":core:common-test"))
    androidTestImplementation(libs.mockk.android)

    testImplementation(libs.bundles.testing)
    testImplementation(project(":core:common-test"))

    implementation(libs.compose.destinations.animations)
    implementation(libs.androidx.material3.android)
    ksp(libs.compose.destinations.ksp)
}
