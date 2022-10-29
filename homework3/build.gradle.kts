plugins {
    java
}

group = "mainbuild"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.google.inject:guice:5.1.0")
    implementation("com.intellij:annotations:12.0")
    implementation("org.projectlombok:lombok:1.18.24")

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    testImplementation("name.falgout.jeffrey.testing.junit5:guice-extension:1.2.1")
    testImplementation("org.mockito:mockito-inline:4.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
    testImplementation("org.mockito:mockito-junit-jupiter:4.8.0")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}


tasks.getByName<Test>("test") {
    useJUnitPlatform()
}