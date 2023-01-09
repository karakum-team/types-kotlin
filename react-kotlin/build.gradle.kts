plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `react-declarations`
}

val reactTypesVersion = property("react-types.version") as String

dependencies {
    implementation(npm("@types/react", reactTypesVersion))

    implementation(wrappers("browser"))
    implementation(wrappers("react-core"))
}
