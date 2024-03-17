package karakum.events

import kotlinx.serialization.json.Json
import java.io.File

object EventDataRegistry {
    lateinit var sourceFile: File

    private val json = Json {
        explicitNulls = false
    }

    val data: List<EventData> by lazy {
        json.decodeFromString(sourceFile.readText())
    }
}
