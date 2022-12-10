import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.22"
}

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/junit/junit
    testImplementation("junit:junit:4.13.2")
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.testng:testng:7.1.0")
}

tasks {
    sourceSets {
        main { java.srcDirs("src/main") }
        test { java.srcDirs("src/test") }
    }

    wrapper {
        gradleVersion = "7.6"
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}