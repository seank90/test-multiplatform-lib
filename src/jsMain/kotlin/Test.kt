import repository.model.Version
import repository.network.KarhooCoreApi

actual var hello = "HELLO WORLD"

actual var testMap: Map<String, String> = mapOf("char" to "x", "char2" to "y", "string" to "zz")
    get() = MapProxy(field).unsafeCast<Map<String, String>>()

var testList: List<String> = listOf("Hello", "World")
    get() = field.toArrayList().unsafeCast<List<String>>()

var testType: Array<String> = arrayOf("hello", "javascript")

class MapProxy<K, V>(private val map: Map<K, V>) {
    @JsName("get")
    operator fun get(key: K): V? = map[key]
}

class ListProxy<E>(private val list: List<E>) {
    @JsName("get")
    operator fun get(key: Int): E? = list[key]
}

fun <E> List<E>.toArrayList(): Array<E> {
    return this.toTypedArray()
}

@JsExport
@kotlin.js.ExperimentalJsExport
fun getVersion() {
    KarhooCoreApi().getVersion({
        console.log(it)
    }, {
        console.log(it)
    })
}