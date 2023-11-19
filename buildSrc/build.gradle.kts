plugins {
    `kotlin-dsl`
}

dependencies {
    with(catalogGradle) {
        implementation(android.gradle.stable)
        implementation(kotlin.gradle)
        implementation(mersey.gradlePlugins)
        implementation(maven.publish.plugin)
    }
}

