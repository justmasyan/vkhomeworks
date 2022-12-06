
dependencies {
    implementation(project(":JooqGenerated"))

    implementation("org.postgresql:postgresql:42.5.0")
    implementation("org.flywaydb:flyway-core:9.8.2")

    implementation("org.eclipse.jetty:jetty-servlet:9.4.33.v20201020")
    implementation("org.eclipse.jetty:jetty-server:9.4.33.v20201020")

    implementation("org.jboss.resteasy:resteasy-guice:3.15.3.Final")
    implementation("org.jboss.resteasy:resteasy-jackson-provider:3.15.3.Final")
    implementation("org.codehaus.jackson:jackson-xc:1.9.13")
    implementation("org.codehaus.jackson:jackson-jaxrs:1.9.13")


    implementation("javax.ws.rs:javax.ws.rs-api:2.1.1")

    compileOnly("javax.servlet:javax.servlet-api:4.0.1")
    compileOnly("jakarta.servlet:jakarta.servlet-api:5.0.0")

    implementation("com.google.inject:guice:4.2.2")
    implementation("com.google.inject.extensions:guice-servlet:4.2.2")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")
    implementation("org.glassfish.hk2:guice-bridge:2.6.0")
    implementation("javax.xml.bind:jaxb-api:2.3.1")

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
}
