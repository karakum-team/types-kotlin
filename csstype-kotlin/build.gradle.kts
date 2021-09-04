plugins {
    id("com.github.turansky.kfc.library")
    `csstype-declarations`
}

val csstypeVersion = property("csstype.version") as String

dependencies {
    implementation(npm("csstype", csstypeVersion))
}
