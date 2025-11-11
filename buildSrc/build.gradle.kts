plugins {
    `kotlin-dsl`
}

dependencies {
    with(catalogGradle) {
        implementation(android.gradle)
        implementation(kotlin.gradle)
        implementation(mersey.gradlePlugins)
        implementation(maven.publish.plugin)
    }
}

