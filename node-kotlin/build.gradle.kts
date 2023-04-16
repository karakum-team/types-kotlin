plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `node-declarations`
}

val nodeTypesVersion = property("node-types.version") as String

dependencies {
    jsMainImplementation(npm("@types/node", nodeTypesVersion))

    jsMainImplementation(wrappers("js"))
    jsMainImplementation(wrappers("web"))

    jsMainImplementation(kotlinxCoroutines("core"))
}
