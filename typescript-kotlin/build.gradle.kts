plugins {
    kfc("library")
    seskar()
    `typescript-declarations`
}

kotlin {
    sourceSets.configureEach {
        languageSettings.optIn("kotlin.contracts.ExperimentalContracts")
    }
}

dependencies {
    implementation(npmv("typescript"))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("js"))
}
