plugins {
    java
}

group = "mainbuild"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

allprojects {
    apply(plugin = "java")

    dependencies {
        implementation("com.intellij:annotations:12.0")

        testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}