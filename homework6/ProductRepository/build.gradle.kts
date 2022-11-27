dependencies {
    implementation(project(":JooqGenerated"))
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}