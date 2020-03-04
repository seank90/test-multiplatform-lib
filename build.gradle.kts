import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.*

plugins {
    kotlin("multiplatform") version Versions.kotlin
    kotlin("plugin.serialization") version Versions.kotlin
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
}

kotlin {
    /* Targets configuration omitted. 
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */

    js() {
        browser {
            dceTask {
                keep("ktor-ktor-io.\$\$importsForInline\$\$.ktor-ktor-io.io.ktor.utils.io")
                keep("multiplatform-lib")
            }
            webpackTask {
                mode = Mode.PRODUCTION
                sourceMaps = false
            }
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.coroutinesCore}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.serializationCommon}")
                implementation("io.ktor:ktor-client-core:${Versions.ktor}")
                implementation("io.ktor:ktor-client-json:${Versions.ktor}")
                implementation("io.ktor:ktor-client-serialization:${Versions.ktor}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.coroutinesCore}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${Versions.serializationCommon}")
                implementation("io.ktor:ktor-client-js:${Versions.ktor}")
                implementation("io.ktor:ktor-client-json-js:${Versions.ktor}")
                implementation("io.ktor:ktor-client-serialization-js:${Versions.ktor}")
                api(npm("text-encoding"))
                api(npm("bufferutil"))
                api(npm("utf-8-validate"))
                api(npm("abort-controller"))
                api(npm("fs"))
            }
        }
        val jsTest by getting {

        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
}