plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    // id("team.karakum.converter")
    `node-declarations`
}

val nodeTypesVersion = property("node-types.version") as String

dependencies {
    implementation(npm("@types/node", nodeTypesVersion))

    implementation(wrappers("js"))
    implementation(wrappers("web"))

    implementation(kotlinxCoroutines("core"))
}
