plugins {
    kotlin("js") version "1.6.10"
}

repositories {
    mavenCentral()
}

dependencies {
}

kotlin {
    js(IR) {
        useCommonJs()
        binaries.executable()
        nodejs()
    }
}
