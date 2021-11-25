import com.shalan.dependencies_manager.DependenciesManager
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.shalan.dependencies")
    kotlin("kapt")
}

val BUILD_ENVIRONMENT = "serverMode"

kapt {
    correctErrorTypes = true
}

val credentialsProperties = Properties()
val credentialsPropertiesFile = File("${project.rootDir}/credentials.properties")
if (credentialsPropertiesFile.exists())
    credentialsProperties.load(FileInputStream(credentialsPropertiesFile))

android {

    buildFeatures {
        dataBinding = true
        buildConfig = true
    }

    compileSdk = 31

    defaultConfig {
        applicationId = "com.shalan.searchgithub"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        resValue("string", "REDIRECTION_URL", "searchgithub.shalan.com")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    flavorDimensions.add(BUILD_ENVIRONMENT)
    productFlavors {
        create("production"){
            dimension = BUILD_ENVIRONMENT
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
            buildConfigField("String", "CLIENT_ID", "\"${credentialsProperties["CLIENT_ID"]}\"")
            buildConfigField("String", "CLIENT_SECRET", "\"${credentialsProperties["CLIENT_SECRET"]}\"")
            buildConfigField("String", "GITHUB_VERSION3_HEADER", "\"application/vnd.github.v3+json\"")
            buildConfigField("String", "GITHUB_REDIRECTION_URL", "\"https://searchgithub.shalan.com/callback\"")
        }
        create("mocking"){
            dimension = BUILD_ENVIRONMENT
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
            buildConfigField("String", "CLIENT_ID", "\"${credentialsProperties["CLIENT_ID"]}\"")
            buildConfigField("String", "CLIENT_SECRET", "\"${credentialsProperties["CLIENT_SECRET"]}\"")
            buildConfigField("String", "GITHUB_VERSION3_HEADER", "\"application/vnd.github.v3+json\"")
            buildConfigField("String", "GITHUB_REDIRECTION_URL", "\"https://searchgithub.shalan.com/callback\"")
        }
    }
}

dependencies {

    implementation(DependenciesManager.CORE_KTX)
    implementation(DependenciesManager.APP_COMPACT)
    implementation(DependenciesManager.MATERIAL_DESIGN)
    implementation(DependenciesManager.CONSTRAINT_LAYOUT)
    testImplementation(DependenciesManager.JUNIT)
    androidTestImplementation(DependenciesManager.ANDROID_TEST_EXT)
    androidTestImplementation(DependenciesManager.ESPRESSO)
    implementation(DependenciesManager.KOIN)
    implementation(DependenciesManager.KOIN_ANDROID)
    implementation(platform(DependenciesManager.OKHTTP_BOM))
    implementation(DependenciesManager.OKHTTP)
    implementation(DependenciesManager.MOSHI)
    implementation(DependenciesManager.MOSHI_KOTLIN)
    kapt(DependenciesManager.MOSHI_KOTLIN_CODE_GENERATION)
    implementation(DependenciesManager.VIEWMODEL)
    implementation(DependenciesManager.LIVEDATA)
    implementation(DependenciesManager.RX_KOTLIN_3)
    implementation(DependenciesManager.RX_ANDROID_3)
    implementation(DependenciesManager.RXJAVA_3)
    implementation(DependenciesManager.RECYCLER_VIEW)
    implementation(DependenciesManager.COIL)
    debugImplementation(DependenciesManager.CHUCKER_DEBUG)
    releaseImplementation(DependenciesManager.CHUCKER_RELEASE)
    implementation(DependenciesManager.HTTP_LOGGING_PRETTY)
    implementation(DependenciesManager.RETROFIT)
    implementation(DependenciesManager.MOSHI_CONVERTER)
    implementation(DependenciesManager.RXJAVA3_RETROFIT_ADAPTER)
    implementation(DependenciesManager.SKELETON_VIEW)
    implementation(DependenciesManager.SCALARS_CONVERTER)
    implementation(DependenciesManager.ROOM_DATABASE)
    kapt(DependenciesManager.ROOM_DATABASE_ANNOTATION)
    implementation(DependenciesManager.ROOM_DATABASE_RXJAVA3)

}