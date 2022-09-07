plugins {
    kotlin("multiplatform") version Versions.kotlin
    id("org.jetbrains.kotlin.plugin.serialization") version Versions.kotlin
    id("com.android.library")
    id("convention.publication")
}

group = "io.github.joreilly"
version = "0.5.2"

repositories {
    google()
    mavenCentral()
}



kotlin {
    android {
        publishAllLibraryVariants()
    }
    jvm()
    iosArm64()
    iosSimulatorArm64()
    iosX64()
    macosX64("macos")

    js {
        browser {
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Ktor
                with(Ktor) {
                    implementation(clientCore)
                    implementation(clientJson)
                    implementation(clientLogging)
                    implementation(contentNegotiation)
                    implementation(json)
                }

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


        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Ktor.clientIos)
            }
        }

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


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}


android {
    compileSdkVersion(32)
    defaultConfig {
        minSdkVersion(21)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    val main by sourceSets.getting {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
    }
}