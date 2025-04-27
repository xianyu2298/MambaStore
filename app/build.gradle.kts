plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.aaa.market"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.aaa.market"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding=true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.navigationRuntime)
    implementation(libs.navigationUi)
    implementation(libs.navigationFragment)

    implementation(libs.constraintlayout)
    implementation(libs.lifecycleLivedataKtx)
    implementation(libs.lifecycleViewmodelKtx)
    implementation(libs.legacySupportV4)
    implementation(libs.recyclerview)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxExtJunit)
    androidTestImplementation(libs.espressoCore)
}
