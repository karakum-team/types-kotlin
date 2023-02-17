plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `typescript-declarations`
}

kotlin {
    sourceSets.configureEach {
        languageSettings.optIn("kotlin.contracts.ExperimentalContracts")
    }
}

dependencies {
    jsMainImplementation(npmv("typescript"))

    jsMainImplementation(wrappers("js"))
}
