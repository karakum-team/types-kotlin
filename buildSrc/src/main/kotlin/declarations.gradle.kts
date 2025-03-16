import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

tasks.named<Delete>("clean") {
    delete("src")
}

val generateDeclarations by tasks.registering {
    dependsOn(":kotlinNpmInstall")
}

tasks.withType<KotlinCompile> {
    dependsOn(generateDeclarations)
}
