plugins {
    id("io.github.turansky.kfc.library")
    id("io.github.turansky.seskar")
    id("team.karakum.converter")
    `node-declarations`
}

val nodeTypesVersion = property("node-types.version") as String
val kotlinWrappersVersion = property("kotlin-wrappers.version") as String

dependencies {
    implementation(npm("@types/node", nodeTypesVersion))

    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-js")

    implementation(kotlinxCoroutines("core"))
}
