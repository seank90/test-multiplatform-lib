package repository.network

import repository.model.Error
import repository.model.Version

interface NetworkAPI {

    val VERSION_METHOD: String
        get() = "https://jsonplaceholder.typicode.com/todos/1"

    fun getVersion(onSuccess: (Version) -> Unit, onError: (Error) -> Unit)

}