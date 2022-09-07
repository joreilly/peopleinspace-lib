
object Versions {
    const val kotlin = "1.7.10"
    const val kotlinCoroutines = "1.6.4"
    const val ktor = "2.1.1"
    const val kotlinxSerialization = "1.3.3"
    const val slf4j = "1.7.30"
    const val junit = "4.13"
}



object Ktor {
    val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    val clientJson = "io.ktor:ktor-client-json:${Versions.ktor}"
    val clientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
    val json = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"

    val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    val clientApache = "io.ktor:ktor-client-apache:${Versions.ktor}"
    val slf4j = "org.slf4j:slf4j-simple:${Versions.slf4j}"
    val clientIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
    val clientCio = "io.ktor:ktor-client-cio:${Versions.ktor}"
    val clientJs = "io.ktor:ktor-client-js:${Versions.ktor}"
}

object Serialization {
    val core = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
}


