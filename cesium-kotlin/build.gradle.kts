plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
    `cesium-declarations`
}

dependencies {
    jsMainImplementation(npmv("@cesium/engine"))
    jsMainImplementation(npmv("@cesium/widgets"))

    jsMainImplementation(libs.wrappers.browser)
}

val syncCesiumEngine by tasks.creating(SyncWrappers::class) {
    from(generatedDir) {
        include("cesium/engine/")
    }
    into(kotlinWrappersDir("kotlin-cesium-engine"))
}

val syncCesiumWidgets by tasks.creating(SyncWrappers::class) {
    from(generatedDir) {
        include("cesium/widgets/")
    }
    into(kotlinWrappersDir("kotlin-cesium-widgets"))
}

val syncWithWrappers by tasks.creating {
    dependsOn(syncCesiumEngine)
    dependsOn(syncCesiumWidgets)
}
