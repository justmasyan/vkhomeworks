plugins {
    java
    id("nu.studer.jooq") version "7.1" apply false
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

subprojects {

    apply{
        plugin("java")
        plugin("nu.studer.jooq")
    }

    dependencies {
        implementation("org.jooq:jooq:3.17.4")
        implementation("org.jooq:jooq-codegen:3.17.4")
        implementation("org.jooq:jooq-meta:3.17.4")

        implementation("org.flywaydb:flyway-core:9.6.0")
        implementation("org.postgresql:postgresql:42.2.9")
        compileOnly("org.projectlombok:lombok:1.18.24")
        annotationProcessor("org.projectlombok:lombok:1.18.24")

        testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }
}
