plugins {
    id("com.github.turansky.kfc.library")
    `typescript-declarations`
}

kotlin {
    sourceSets.configureEach {
        languageSettings.optIn("kotlin.contracts.ExperimentalContracts")
    }
}

dependencies {
    implementation(npmv("typescript"))
}
