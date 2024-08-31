plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
    `react-query-declarations`
}

kotlin {
    sourceSets.configureEach {
        languageSettings.optIn("kotlin.ExperimentalStdlibApi")
    }
}

dependencies {
    jsMainImplementation(npmv("@tanstack/react-query"))

    jsMainImplementation(libs.wrappers.web)
    jsMainImplementation(libs.wrappers.react.core)
}

val syncCoreWrappers by tasks.creating(SyncWrappers::class) {
    from(generatedDir) {
        include("tanstack/query/")
    }
    into(kotlinWrappersDir("kotlin-tanstack-query-core"))
}

val syncReactWrappers by tasks.creating(SyncWrappers::class) {
    from(generatedDir) {
        include("tanstack/react/")
    }
    into(kotlinWrappersDir("kotlin-tanstack-react-query"))
}

val syncWithWrappers by tasks.creating {
    dependsOn(syncCoreWrappers)
    dependsOn(syncReactWrappers)
}
