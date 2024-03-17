package karakum.events

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.File

object EventDataRegistry {
    lateinit var sourceFile: File

    private val json = Json {
        // temp
        ignoreUnknownKeys = true
    }

    val data: List<EventData> by lazy {
        json.decodeFromString(
            ListSerializer(EventData.serializer()),
            sourceFile.readText(),
        )
    }
}
