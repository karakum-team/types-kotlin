plugins {
    id("io.github.turansky.kfc.library")
    `popper-declarations`
}

val popperVersion = property("popper.version") as String
val kotlinWrappersVersion = property("kotlin-wrappers.version") as String

dependencies {
    implementation(npm("@popperjs/core", popperVersion))

    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions")
}
