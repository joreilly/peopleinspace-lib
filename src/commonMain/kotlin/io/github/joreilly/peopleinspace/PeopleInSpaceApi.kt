package io.github.joreilly.peopleinspace

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class AstroResult(val message: String, val number: Int, val people: List<Assignment>)

@Serializable
data class Assignment(val craft: String, val name: String, var personImageUrl: String? = "", var personBio: String? = "")

@Serializable
data class IssPosition(val latitude: Double, val longitude: Double)

@Serializable
data class IssResponse(val message: String, val iss_position: IssPosition, val timestamp: Long)


class PeopleInSpaceApi(private val baseUrl: String = "https://people-in-space-proxy.ew.r.appspot.com")  {

    private val json = Json { isLenient = true; ignoreUnknownKeys = true }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(json)
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }

    suspend fun fetchPeople() = client.get("$baseUrl/astros.json").body<AstroResult>()
    suspend fun fetchISSPosition() = client.get("$baseUrl/iss-now.json").body<IssResponse>()
}
