import android.util.Log
import repository.network.CoreApi

actual var hello: String = "Hello Andorid"
actual var testMap: Map<String, String> = mapOf("X" to "Y")

fun getVersion() {
    CoreApi().getVersion({
        Log.d("Response::", it.toString())
    }, {
        Log.e("Error::", it.toString())
    })
}