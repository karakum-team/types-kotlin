plugins {
    id("com.github.turansky.kfc.library")
    `react-declarations`
}

val reactTypesVersion = property("react-types.version") as String
val kotlinWrappersVersion = "0.0.1-pre.226-kotlin-1.5.21"

dependencies {
    implementation(npm("@types/react", reactTypesVersion))

    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:${kotlinWrappersVersion}"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
}
