package repository.model

import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.Serializable

@Serializable
data class Version(val title: String,
                   val userId: Int,
                   val id: Int,
                   val completed: Boolean){}