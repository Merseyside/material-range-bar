plugins {
    with(catalogPlugins.plugins) {
        plugin(android.library)
        plugin(kotlin.android)
        id(mersey.android.extension.id())
        id(mersey.kotlin.extension.id())
    }
    `maven-publish-plugin`
}

android {
    namespace = "com.appyvet.rangebar"
    compileSdk = 34
    defaultConfig {
        minSdk = 23
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("release") {
            isMinifyEnabled = false
            consumerProguardFiles("proguard-rules.pro")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

//    packagingOptions {
//        resources.excludes.addAll(
//            listOf(
//                "META-INF/DEPENDENCIES",
//                "META-INF/LICENSE",
//                "META-INF/LICENSE.txt",
//                "META-INF/license.txt",
//                "META-INF/NOTICE",
//                "META-INF/NOTICE.txt",
//                "META-INF/notice.txt",
//                "META-INF/ASL2.0",
//                "META-INF/*.kotlin_module",
//                "META-INF/INDEX.LIST"
//            )
//        )
//    }
}

dependencies {
    implementation(androidLibs.androidx.core)
}