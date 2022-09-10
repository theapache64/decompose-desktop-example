import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("org.jetbrains.compose") version "1.2.0-alpha01-dev774"
}

group = "com.theapache64"
version = "1.0.1"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(compose.desktop.currentOs)
    // Decompose : Decompose
    implementation("com.arkivanov.decompose:decompose:1.0.0-alpha-04")
    implementation("com.arkivanov.decompose:extensions-compose-jetbrains:1.0.0-alpha-04")

}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "com.theapache64.dde.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Decompose Desktop Example"
        }
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