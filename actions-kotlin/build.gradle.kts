plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `actions-declarations`
}

dependencies {
    jsMainImplementation(npmv("@actions/artifact"))
    jsMainImplementation(npmv("@actions/cache"))
    jsMainImplementation(npmv("@actions/core"))
    jsMainImplementation(npmv("@actions/exec"))
    // jsMainImplementation(npmv("@actions/github"))
    jsMainImplementation(npmv("@actions/glob"))
    jsMainImplementation(npmv("@actions/http-client"))
    jsMainImplementation(npmv("@actions/io"))
    jsMainImplementation(npmv("@actions/tool-cache"))

    jsMainImplementation(wrappers("node"))

    jsMainImplementation(npmv("@types/web"))
    jsMainImplementation(npmv("@types/serviceworker"))
    jsMainImplementation(devNpm("typescript", "5.0.3"))
    jsMainImplementation(npmv("@webref/idl"))

    jsMainImplementation(kotlinxCoroutines("core"))
}
