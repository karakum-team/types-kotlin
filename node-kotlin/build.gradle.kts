plugins {
    kfc("library")
    seskar()
    // id("team.karakum.converter")
    `node-declarations`
}

val nodeTypesVersion = property("node-types.version") as String

dependencies {
    implementation(npm("@types/node", nodeTypesVersion))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("js"))
    implementation(kotlinw("web"))

    implementation(kotlinxCoroutines("core"))
}
