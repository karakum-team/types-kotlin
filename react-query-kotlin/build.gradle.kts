plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `react-query-declarations`
}

val reactQueryVersion = property("react-query.version") as String

dependencies {
    jsMainImplementation(npm("@tanstack/react-query", reactQueryVersion))

    jsMainImplementation(wrappers("web"))
    jsMainImplementation(wrappers("react-core"))

    jsMainImplementation(seskarCore())
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
