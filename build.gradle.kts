import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.31"
    application
}

group = "me.vikramsingh"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven(url = "https://dl.bintray.com/kotlin/kotlinx")
    maven(url = "https://dl.bintray.com/kotlin/ktor")
}

sourceSets {
    map { it.java.srcDir("src/${it.name}/kotlin") }
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation(Dependencies.Kotlin.Test.Junit5.api)
    testRuntimeOnly(Dependencies.Kotlin.Test.Junit5.engine)

    testImplementation(Dependencies.Kotlin.Test.mockk)
    testImplementation(Dependencies.Kotlin.Test.truth)
    testImplementation(Dependencies.Kotlin.Test.ktorServer)

    //ktor logging
    implementation(Dependencies.Java.logback)
    implementation(Dependencies.Kotlin.logging)

    //ktor server
    implementation(Dependencies.Ktor.Server.core)
    implementation(Dependencies.Ktor.Server.netty)

    //KTor features
    implementation(Dependencies.Ktor.Client.websockets)
    implementation(Dependencies.Ktor.Client.locations)
    implementation(Dependencies.Ktor.Client.networkTls)
    implementation(Dependencies.Ktor.Client.auth)
    implementation(Dependencies.Ktor.Client.jwt)

    //KTor features
    implementation(Dependencies.Ktor.Client.jsonJvm)
    implementation(Dependencies.Ktor.Client.jackson)
    implementation(Dependencies.Java.Jackson.params)

    //DI
    implementation(Dependencies.Kodein.Ktor.controller)
    implementation(Dependencies.Kodein.Ktor.jvm)

    //Threading
    implementation(Dependencies.Kotlin.coroutineCore)

    //Db
    implementation(Dependencies.Kotlin.Database.postGresSQL)
    implementation(Dependencies.Kotlin.Database.exposedCore)
    implementation(Dependencies.Kotlin.Database.exposedJdbc)
    implementation(Dependencies.Kotlin.Database.exposedDao)

    implementation(Dependencies.Kotlin.hikariCp)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}