buildscript {
    val kotlinVersion = "1.0.6"
    extra["kotlinVersion"] = kotlinVersion

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0-M2")
    }
}

apply {
    plugin("kotlin")
    plugin("application")
    plugin("org.junit.platform.gradle.plugin")
}

val mainClassName = "io.frontierrobotics.i2c.api.ApplicationKt"

group = "io.frontierrobotics"
version = "0.1.0"

description = "I2C API"

repositories {
    jcenter()
    mavenCentral()
    maven {
        setUrl("http://dl.bintray.com/wasabifx/wasabifx")
    }
    maven {
        setUrl("http://repository.jetbrains.com/all")
    }
}

val kotlinVersion = extra["kotlinVersion"] as String

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    compile("com.pi4j:pi4j-core:1.0")
    compile("org.wasabifx:wasabi:0.3.37")
    testCompile("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testCompile("com.nhaarman:mockito-kotlin:0.6.1")
    testCompile("org.junit.platform:junit-platform-console:1.0.0-M2")
    testCompile("org.jetbrains.spek:spek-api:1.0.89")
    testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.0.89")
}
