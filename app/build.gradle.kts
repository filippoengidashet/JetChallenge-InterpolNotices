plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.filippoengidashet.challenge4.lloyds"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.filippoengidashet.challenge4.lloyds"
        minSdk = 24
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

kapt {
    correctErrorTypes = true
    arguments {
        arg("dagger.hilt.android.internal.disableAndroidSuperclassValidation", "true")
        arg("dagger.hilt.internal.useAggregatingRootProcessor", "true")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //Multidex (new .DEX file for over 64K methods)
    implementation("androidx.multidex:multidex:2.0.1")

    //App Launcher Screen - API
    implementation("androidx.core:core-splashscreen:1.0.1")

    //Dependency Injection/Management (Dagger + Hilt)
    implementation("com.google.dagger:hilt-android:2.52")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //Compose navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.runtime.ktx)

    //Paginated API Request on scroll
    implementation("androidx.paging:paging-runtime:3.3.6")
    implementation("androidx.paging:paging-compose:3.3.6")

    //View Pager and indicator for Jetpack compose
    implementation("com.google.accompanist:accompanist-pager:0.36.0")

    //Network (Retrofit)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    //Image Loading & Caching - API
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")

    //Data persistence (DataStore Preferences)
    implementation("androidx.datastore:datastore-preferences:1.1.4")

//    //Data persistence (Room Database)
//    implementation("androidx.room:room-runtime:2.5.0")
//    kapt ("androidx.room:room-compiler:2.5.0")
//    implementation("androidx.room:room-ktx:2.5.0")// For Coroutines Support (optional)

    testImplementation(libs.test.coroutines)
    testImplementation(libs.mockito)
    testImplementation(libs.mockk.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
