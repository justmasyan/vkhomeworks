plugins {
    java
}

group = "mainbuild"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    implementation("com.google.code.gson:gson:2.9.1")
    implementation("com.google.inject:guice:5.1.0")
    implementation("com.intellij:annotations:12.0")
    implementation("org.projectlombok:lombok:1.18.24")

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testImplementation("name.falgout.jeffrey.testing.junit5:guice-extension:1.2.1")
    testImplementation("org.mockito:mockito-core:4.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}


tasks.getByName<Test>("test") {
    useJUnitPlatform()
}