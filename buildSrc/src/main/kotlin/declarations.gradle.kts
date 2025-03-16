tasks.named<Delete>("clean") {
    delete("src")
}

val generateDeclarations by tasks.registering {
    dependsOn(":kotlinNpmInstall")
}

tasks.named("compileKotlinJs") {
    dependsOn(generateDeclarations)
}
