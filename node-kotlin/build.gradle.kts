plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    // id("team.karakum.converter")
    `node-declarations`
}

val nodeTypesVersion = property("node-types.version") as String

dependencies {
    jsMainImplementation(npm("@types/node", nodeTypesVersion))

    jsMainImplementation(wrappers("js"))
    jsMainImplementation(wrappers("web"))

    jsMainImplementation(kotlinxCoroutines("core"))
}
