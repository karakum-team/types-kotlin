plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
    `cesium-declarations`
}

dependencies {
    jsMainImplementation(npmv("@cesium/engine"))
    jsMainImplementation(npmv("@cesium/widgets"))

    jsMainImplementation(kotlinWrappers.browser)
}

val syncCesiumEngine by tasks.registering(SyncWrappers::class) {
    from(jsGeneratedDir) {
        include("cesium/engine/")
    }
    into(kotlinWrappersDir("kotlin-cesium-engine"))
}

val syncCesiumWidgets by tasks.registering(SyncWrappers::class) {
    from(jsGeneratedDir) {
        include("cesium/widgets/")
    }
    into(kotlinWrappersDir("kotlin-cesium-widgets"))
}

val syncWithWrappers by tasks.registering {
    dependsOn(syncCesiumEngine)
    dependsOn(syncCesiumWidgets)
}
