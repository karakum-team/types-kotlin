package karakum.browser

import java.net.URL

private val DENO_UNSTABLE_URL =
    URL("https://raw.githubusercontent.com/denoland/deno/main/cli/tsc/dts/lib.deno.unstable.d.ts")

internal val DENO_UNSTABLE_CONTENT by lazy {
    DENO_UNSTABLE_URL.openStream()
        .use { stream -> String(stream.readAllBytes()) }
        .replace("\n  /** @category Temporal */", "")
        .replace("\n   *\n   * @category Temporal", "")
}
