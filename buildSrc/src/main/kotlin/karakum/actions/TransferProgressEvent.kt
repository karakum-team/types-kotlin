package karakum.actions

internal fun TransferProgressEvent(): ConversionResult =
    ConversionResult(
        name = "TransferProgressEvent",
        body = """
        // Copy of `TransferProgressEvent` from '@azure/ms-rest-js'
        sealed external interface TransferProgressEvent {
          /**
           * The number of bytes loaded so far.
           */
          val loadedBytes: JsLong
        }
        """.trimIndent(),
    )
