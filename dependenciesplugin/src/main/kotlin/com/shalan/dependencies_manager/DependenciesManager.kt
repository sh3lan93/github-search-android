package com.shalan.dependencies_manager

import org.gradle.api.Plugin
import org.gradle.api.Project

class DependenciesManager : Plugin<Project> {

    companion object {

        const val APP_COMPACT = "androidx.appcompat:appcompat:${VersionsManager.APP_COMPACT}"

        const val CORE_KTX = "androidx.core:core-ktx:${VersionsManager.CORE_KTX}"

        const val MATERIAL_DESIGN =
            "com.google.android.material:material:${VersionsManager.MATERIAL_DESIGN}"

        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${VersionsManager.CONSTRAINT_LAYOUT}"

        const val JUNIT = "junit:junit:${VersionsManager.JUNIT}"

        const val ANDROID_TEST_EXT = "androidx.test.ext:junit:${VersionsManager.ANDROID_TEST_EXT}"

        const val ESPRESSO = "androidx.test.espresso:espresso-core:${VersionsManager.ESPRESSO}"

        const val KOIN = "io.insert-koin:koin-core:${VersionsManager.KOIN}"

        const val KOIN_ANDROID = "io.insert-koin:koin-android:${VersionsManager.KOIN}"

        const val KOIN_ANDROID_VIEWMODEL =
            "io.insert-koin:koin-android-viewmodel:${VersionsManager.KOIN}"

        const val OKHTTP_BOM = "com.squareup.okhttp3:okhttp-bom:${VersionsManager.OKHTTP_BOM}"

        const val OKHTTP = "com.squareup.okhttp3:okhttp"

        const val MOSHI = "com.squareup.moshi:moshi:${VersionsManager.MOSHI}"

        const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:${VersionsManager.MOSHI}"

        const val MOSHI_KOTLIN_CODE_GENERATION =
            "com.squareup.moshi:moshi-kotlin-codegen:${VersionsManager.MOSHI}"

        const val RXJAVA_3 = "io.reactivex.rxjava3:rxjava:${VersionsManager.RXJAVA_3}"

        const val RX_KOTLIN_3 = "io.reactivex.rxjava3:rxkotlin:${VersionsManager.RX_KOTLIN_3}"

        const val VIEWMODEL =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${VersionsManager.LIFECYCLE}"

        const val LIVEDATA =
            "androidx.lifecycle:lifecycle-livedata-ktx:${VersionsManager.LIFECYCLE}"

        const val RECYCLER_VIEW =
            "androidx.recyclerview:recyclerview:${VersionsManager.RECYCLER_VIEW}"

        const val RX_ANDROID_3 = "io.reactivex.rxjava3:rxandroid:${VersionsManager.RX_ANDROID_3}"

        const val COIL = "io.coil-kt:coil:${VersionsManager.COIL}"

        const val CHUCKER_DEBUG =
            "com.github.chuckerteam.chucker:library:${VersionsManager.CHUCKER}"

        const val CHUCKER_RELEASE =
            "com.github.chuckerteam.chucker:library-no-op:${VersionsManager.CHUCKER}"

        const val HTTP_LOGGING_PRETTY =
            "com.github.ihsanbal:LoggingInterceptor:${VersionsManager.HTTP_LOGGING_PRETTY}"

        const val RETROFIT = "com.squareup.retrofit2:retrofit:${VersionsManager.RETROFIT}"

        const val MOSHI_CONVERTER =
            "com.squareup.retrofit2:converter-moshi:${VersionsManager.RETROFIT}"

        const val RXJAVA3_RETROFIT_ADAPTER =
            "com.github.akarnokd:rxjava3-retrofit-adapter:${VersionsManager.RXJAVA3_RETROFIT_ADAPTER}"

        const val SKELETON_VIEW = "com.faltenreich:skeletonlayout:${VersionsManager.SKELETON_VIEW}"

        const val SCALARS_CONVERTER =
            "com.squareup.retrofit2:converter-scalars:${VersionsManager.RETROFIT}"

        const val ROOM_DATABASE = "androidx.room:room-runtime:${VersionsManager.ROOM_DATABASE}"

        const val ROOM_DATABASE_ANNOTATION =
            "androidx.room:room-compiler:${VersionsManager.ROOM_DATABASE}"

        const val ROOM_DATABASE_RXJAVA3 =
            "androidx.room:room-rxjava3:${VersionsManager.ROOM_DATABASE}"
    }

    override fun apply(target: Project) {

    }


}