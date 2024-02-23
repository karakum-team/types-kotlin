// Automatically generated - do not modify!

package web.media.streams

import js.objects.JsPlainObject
import web.media.streams.MediaStream
import web.media.streams.MediaStreamConstraints
import web.media.streams.MediaTrackConstraints

@JsPlainObject
sealed external interface MediaStreamConstraints {
var audio: MediaTrackConstraints /* | Boolean */?
var peerIdentity: String?
var preferCurrentTab: Boolean?
var video: MediaTrackConstraints /* | Boolean */?
}
