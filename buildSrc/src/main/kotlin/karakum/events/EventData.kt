package karakum.events

import kotlinx.serialization.Serializable

@Serializable
data class EventData(
    val `interface`: String,
)
