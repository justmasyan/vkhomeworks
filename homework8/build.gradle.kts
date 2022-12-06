plugins {
    java
    id("nu.studer.jooq") version "7.1" apply false
}

group = "org.example"
version = "1.0-SNAPSHOT"

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
    }
}