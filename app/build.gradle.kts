plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.ecommericeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ecommericeapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    viewBinding {
        enable=true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.firebase:firebase-database:20.3.1")
    implementation("androidx.activity:activity:1.8.0")
    testImplementation("junit:junit:4.13.2")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("com.github.smarteist:autoimageslider:1.3.9")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.razorpay:checkout:1.6.33")
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")
    implementation ("com.google.firebase:firebase-messaging:23.4.1")








    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}