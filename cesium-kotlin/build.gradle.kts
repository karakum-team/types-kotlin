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
    from(commonGeneratedDir) {
        include("cesium/engine/")
    }
    into(kotlinWrappersCommonDir("kotlin-cesium-engine"))
}

val syncCesiumWidgets by tasks.registering(SyncWrappers::class) {
    from(commonGeneratedDir) {
        include("cesium/widgets/")
    }
    into(kotlinWrappersCommonDir("kotlin-cesium-widgets"))
}

val syncWithWrappers by tasks.registering {
    dependsOn(syncCesiumEngine)
    dependsOn(syncCesiumWidgets)
}
