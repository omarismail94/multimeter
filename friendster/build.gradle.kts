import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    id("java-library")
    kotlin("jvm")
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

val friends = configurations.create("friends") {
    isCanBeResolved = true
    isCanBeConsumed = false
    isTransitive = false
}

configurations.findByName("implementation")?.extendsFrom(friends)

tasks.withType<KotlinCompile>().configureEach {
    friendPaths.from(friends.incoming.artifactView { }.files)
}

dependencies {
    friends(project(":lib"))
}