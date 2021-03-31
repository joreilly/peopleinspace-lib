package io.github.joreilly.peopleinspace

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class AstroResult(val message: String, val number: Int, val people: List<Assignment>)

@Serializable
data class Assignment(val craft: String, val name: String)

@Serializable
data class IssPosition(val latitude: Double, val longitude: Double)

@Serializable
data class IssResponse(val message: String, val iss_position: IssPosition, val timestamp: Long)


class PeopleInSpaceApi(private val baseUrl: String = "http://api.open-notify.org")  {

    private val json = Json { isLenient = true; ignoreUnknownKeys = true }

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }

    suspend fun fetchPeople() = client.get<AstroResult>("$baseUrl/astros.json")
    suspend fun fetchISSPosition() = client.get<IssResponse>("$baseUrl/iss-now.json")
}
