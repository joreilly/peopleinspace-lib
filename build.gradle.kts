plugins {
    kotlin("multiplatform") version Versions.kotlin
    id("kotlinx-serialization") version Versions.kotlin
    id("com.android.library")
    id("convention.publication")
}

group = "io.github.joreilly"
version = "0.4.0"

repositories {
    google()
    mavenCentral()
    jcenter()
}



kotlin {
    android {
        publishAllLibraryVariants()
    }
    jvm()
    ios()

    val sdkName: String? = System.getenv("SDK_NAME")
    val isWatchOSDevice = sdkName.orEmpty().startsWith("watchos")
    if (isWatchOSDevice) {
        watchosArm64("watch")
    } else {
        watchosX86("watch")
    }

    macosX64("macos")

    js {
        browser {
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Ktor
                implementation(Ktor.clientCore)
                implementation(Ktor.clientJson)
                implementation(Ktor.clientLogging)
                implementation(Ktor.clientSerialization)

                // Kotlinx Serialization
                implementation(Serialization.core)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Ktor.clientAndroid)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(Ktor.clientApache)
                implementation(Ktor.slf4j)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(Ktor.clientIos)
            }
        }

//        val watchosMain by getting {
//            dependencies {
//                implementation(Ktor.clientIos)
//            }
//        }

        val macosMain by getting {
            dependencies {
                implementation(Ktor.clientCio)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(Ktor.clientJs)
            }
        }

    }
}


android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(21)
    }

    val main by sourceSets.getting {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
    }
}