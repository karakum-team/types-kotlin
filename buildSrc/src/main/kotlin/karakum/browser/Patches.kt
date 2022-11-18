package karakum.browser

internal fun String.applyPatches(): String =
    patchVideoFrameCallback()
        .patchQuerySelectors()
        .splitUnion("string | URL")
        .splitUnion("string | Blob")
        .splitUnion("RequestInfo | URL")
        .splitUnion("RequestInfo", "Request | string")
        .splitUnion("MediaStreamTrack | string")
        .splitUnion("Path2D | string")
        .replace("\n    getContext(contextId: string, options?: any): RenderingContext | null;", "")
        .replace("quality?: any", "quality?: number")
        .replace("LockGrantedCallback): Promise<any>", "LockGrantedCallback): Promise<void>")
        .replace("(lock: Lock | null): any", "(lock: Lock | null): void")
        .replace("""readonly kind: "file";""", """/* val kind: "file" */""")
        .replace("""readonly kind: "directory";""", """/* val kind: "directory" */""")
        .replace("clearWatch(watchId: number)", "clearWatch(watchId: $GEOLOCATION_WATCH_ID)")
        .replace(": PositionOptions): number;", ": PositionOptions): $GEOLOCATION_WATCH_ID;")
        .replace(
            Regex("""forEach\(callbackfn\: \(value\: (\w+), .+?, thisArg\?\: any\)\: void\;"""),
            "forEach(action: (item: $1) => void): void;"
        )
        .replace(
            "arg?: boolean | ScrollIntoViewOptions",
            "options?: ScrollIntoViewOptions",
        )

private fun String.patchVideoFrameCallback(): String =
    replace(
        "cancelVideoFrameCallback(handle: number): void;",
        "cancelVideoFrameCallback(requestId: $VIDEO_FRAME_REQUEST_ID): void;"
    )
        .replace(
            "requestVideoFrameCallback(callback: VideoFrameRequestCallback): number;",
            "requestVideoFrameCallback(callback: VideoFrameRequestCallback): $VIDEO_FRAME_REQUEST_ID;"
        )

private fun String.patchQuerySelectors(): String =
    this
        .replace(
            "querySelector<K extends keyof HTMLElementTagNameMap>(selectors: K): HTMLElementTagNameMap[K] | null;",
            "querySelector<T extends HTMLElement>(selectors: HtmlTagName<T>): T | null;"
        )
        .replace(
            "querySelector<K extends keyof SVGElementTagNameMap>(selectors: K): SVGElementTagNameMap[K] | null;",
            "querySelector<T extends SVGElement>(selectors: SvgTagName<T>): T | null;"
        )
        .replace(
            "querySelector<E extends Element = Element>(selectors: string): E | null;",
            "querySelector(selectors: string): Element | null;"
        )
        .replace(
            "querySelectorAll<K extends keyof HTMLElementTagNameMap>(selectors: K): NodeListOf<HTMLElementTagNameMap[K]>;",
            "querySelectorAll<T extends HTMLElement>(selectors: HtmlTagName<T>): NodeListOf<T>;"
        )
        .replace(
            "querySelectorAll<K extends keyof SVGElementTagNameMap>(selectors: K): NodeListOf<SVGElementTagNameMap[K]>;",
            "querySelectorAll<T extends SVGElement>(selectors: SvgTagName<T>): NodeListOf<T>;"
        )
        .replace(
            "querySelectorAll<E extends Element = Element>(selectors: string): NodeListOf<E>;",
            "querySelectorAll(selectors: string): NodeListOf<Element>;"
        )

internal fun String.splitUnion(
    union: String,
    unionBody: String? = null,
): String {
    val (first, second) = (unionBody ?: union).split(" | ")

    return splitToSequence("\n")
        .flatMap { line ->
            if ("?: $union" in line) {
                sequenceOf(
                    line.replace("?: $union", "?: $first"),
                    line.replace("?: $union", ": $second"),
                )
            } else sequenceOf(line)
        }
        .flatMap { line ->
            if (": $union" in line) {
                sequenceOf(
                    line.replace(": $union", ": $first"),
                    line.replace(": $union", ": $second"),
                )
            } else sequenceOf(line)
        }
        .joinToString("\n")
}
