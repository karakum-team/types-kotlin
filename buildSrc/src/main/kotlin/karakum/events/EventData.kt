package karakum.events

import kotlinx.serialization.Serializable

@Serializable
data class EventData(
    val src: Src,
    val href: String?,
    val type: String,
    val `interface`: String,
)
