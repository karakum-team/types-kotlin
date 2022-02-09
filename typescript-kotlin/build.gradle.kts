plugins {
    id("io.github.turansky.kfc.library")
    `typescript-declarations`
}

kotlin {
    sourceSets.configureEach {
        languageSettings.optIn("kotlin.contracts.ExperimentalContracts")
    }
}

val kotlinWrappersVersion = property("kotlin-wrappers.version") as String

dependencies {
    implementation(npmv("typescript"))

    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions")
}
