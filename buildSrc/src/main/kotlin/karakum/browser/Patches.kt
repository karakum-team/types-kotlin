package karakum.browser

internal fun String.applyPatches(): String =
    patchVideoFrameCallback()
        .replace("\n    getContext(contextId: string, options?: any): RenderingContext | null;", "")
        .replace("quality?: any", "quality?: number")

internal fun String.patchVideoFrameCallback(): String =
    replace(
        "cancelVideoFrameCallback(handle: number): void;",
        "cancelVideoFrameCallback(requestId: $VIDEO_FRAME_REQUEST_ID): void;"
    )
        .replace(
            "requestVideoFrameCallback(callback: VideoFrameRequestCallback): number;",
            "requestVideoFrameCallback(callback: VideoFrameRequestCallback): $VIDEO_FRAME_REQUEST_ID;"
        )
