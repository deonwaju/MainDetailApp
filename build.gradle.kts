// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.kotlin) apply false
    alias(libs.plugins.jvm) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.spotless)
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.parcelize) apply false
    alias(libs.plugins.ksp) apply false
}