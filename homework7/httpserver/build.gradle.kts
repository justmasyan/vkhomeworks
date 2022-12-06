dependencies {
    implementation("org.postgresql:postgresql:42.2.9")
    implementation("org.flywaydb:flyway-core:9.6.0")
    implementation("org.eclipse.jetty:jetty-servlet:11.0.12")
    implementation("org.eclipse.jetty:jetty-server:11.0.12")
    implementation(project(":JooqGenerated"))
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}