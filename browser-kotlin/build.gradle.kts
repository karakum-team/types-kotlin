plugins {
    id("io.github.turansky.kfc.library")
    id("io.github.turansky.seskar")
    `browser-declarations`
}

val kotlinWrappersVersion = property("kotlin-wrappers.version") as String

dependencies {
    implementation(npmv("typescript"))

    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-js")
}
