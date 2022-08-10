plugins {
    id("io.github.turansky.kfc.library")
    id("io.github.turansky.seskar")
    id("team.karakum.converter")
    `node-declarations`
}

val nodeTypesVersion = property("node-types.version") as String

dependencies {
    implementation(npm("@types/node", nodeTypesVersion))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("js"))

    implementation(kotlinxCoroutines("core"))
}
