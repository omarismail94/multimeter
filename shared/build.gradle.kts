plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
}

kotlin {
    androidLibrary {
        namespace = "com.example.shared"
        compileSdk = 36
        minSdk = 24
    }
    jvm()

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
            }
        }

        val androidAndJvm by creating {
            dependsOn(commonMain.get())
        }

        androidMain {
            dependsOn(androidAndJvm)
        }

        jvmMain {
            dependsOn(androidAndJvm)
        }
    }
}