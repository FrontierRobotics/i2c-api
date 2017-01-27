buildscript {
    val springBootVersion = "1.4.3.RELEASE"
    val kotlinVersion = "1.0.6"
    extra["kotlinVersion"] = kotlinVersion

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        classpath("org.jetbrains.kotlin:kotlin-noarg:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.0-M2")
    }
}

apply {
    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("kotlin-jpa")
    plugin("org.springframework.boot")
}

group = "io.frontierrobotics"
version = "0.1.0"

description = "I2C API"

configure<JavaPluginConvention> {
    setSourceCompatibility(1.8)
    setTargetCompatibility(1.8)
}

repositories {
    jcenter()
    mavenCentral()
    maven {
        setUrl("http://repository.jetbrains.com/all")
    }
}

val kotlinVersion = extra["kotlinVersion"] as String

dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    compile("com.pi4j:pi4j-core:1.0")
    compile("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.8.4")
    testCompile("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testCompile("org.springframework.boot:spring-boot-starter-test")
    testCompile("com.nhaarman:mockito-kotlin:0.6.1")
    testCompile("org.junit.platform:junit-platform-console:1.0.0-M2")
    testCompile("org.jetbrains.spek:spek-api:1.0.89")
    testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.0.89")
}
