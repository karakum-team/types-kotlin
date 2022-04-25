plugins {
    `java-gradle-plugin`
    kotlin("jvm")
}

val embeddedJsLibrary by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
}

dependencies {
    compileOnly(kotlin("gradle-plugin"))

    embeddedJsLibrary(project(":karakum-converter", embeddedJsLibrary.name))
}

val embeddedJsLibraryZip by tasks.registering(Zip::class) {
    dependsOn(embeddedJsLibrary)

    archiveFileName.set("karakum.zip")

    from(embeddedJsLibrary.files)
}

tasks.jar {
    from(embeddedJsLibraryZip)
}

gradlePlugin {
    val karakumConverter by plugins.creating {
        id = "team.karakum.converter"
        implementationClass = "team.karakum.converter.gradle.plugin.KarakumConverterPlugin"
    }
}
