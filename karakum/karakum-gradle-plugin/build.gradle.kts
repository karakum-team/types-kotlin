plugins {
    `java-gradle-plugin`
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

val embeddedJsLibrary by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
}

dependencies {
    compileOnly(kotlin("gradle-plugin"))

    embeddedJsLibrary(
        project(
            mapOf(
                "path" to ":karakum-converter",
                "configuration" to "embeddedJsLibrary"
            )
        )
    )
}

val embeddedJsLibraryFileName = "karakum.zip"
val embeddedJsLibraryDestinationDirectory = layout.buildDirectory.dir("tmp")

val embeddedJsLibraryZip by tasks.registering(Zip::class) {
    dependsOn(embeddedJsLibrary)

    archiveFileName.set(embeddedJsLibraryFileName)
    destinationDirectory.set(embeddedJsLibraryDestinationDirectory)

    from(embeddedJsLibrary.files)
}

tasks.named<Jar>("jar") {
    dependsOn(embeddedJsLibraryZip)

    from(embeddedJsLibraryDestinationDirectory.get().file(embeddedJsLibraryFileName))
}

gradlePlugin {
    val karakumConverter by plugins.creating {
        id = "team.karakum.converter"
        implementationClass = "team.karakum.converter.gradle.plugin.KarakumConverterPlugin"
    }
}
