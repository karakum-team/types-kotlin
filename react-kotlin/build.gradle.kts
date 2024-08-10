plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `react-declarations`
}

dependencies {
    jsMainImplementation(npmv("@types/react"))

    jsMainImplementation(wrappers("browser"))
    jsMainImplementation(wrappers("react-core"))
}

val syncWithWrappers by tasks.creating(SyncWrappers::class) {
    from(generatedDir)
    into(kotlinWrappersDir("kotlin-react-dom"))
}
