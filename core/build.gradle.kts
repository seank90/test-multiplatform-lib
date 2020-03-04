import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.*

group = "org.example"
version = "1.0-SNAPSHOT"

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.native.cocoapods")
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

    android()

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

android {
    compileSdkVersion(App.compileSdk)

    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesCore}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.serializationCommon}")
    implementation("io.ktor:ktor-client-android:${Versions.ktor}")
    implementation("io.ktor:ktor-client-json-jvm:${Versions.ktor}")
    implementation("io.ktor:ktor-client-serialization-jvm:${Versions.ktor}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    kotlinOptions.jvmTarget = "1.8"
}
