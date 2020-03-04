package repository.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import repository.model.Error
import repository.model.Version

class KarhooCoreApi(
    val engine: HttpClientEngine? = null,
    val coroutineScope: CoroutineScope = GlobalScope
) : NetworkAPI {

    private val client: HttpClient by lazy {
        return@lazy engine?.let {
            HttpClient(it) {
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }
        } ?: kotlin.run {
            HttpClient() {
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }
        }
    }

    override fun getVersion(onSuccess: (Version) -> Unit, onError: (Error) -> Unit) {
        coroutineScope.launch {
            try {
                val resp = client.get<Version>(VERSION_METHOD)
                onSuccess(resp)
            } catch (e: Exception) {
                print(e.message)
                onError(Error())
            }
        }
    }

}