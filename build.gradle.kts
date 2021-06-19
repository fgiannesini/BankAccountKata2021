plugins {
    java
}

group = "fr.giannesini"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("javax.money:money-api:1.1")
    implementation("org.javamoney:moneta:1.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testImplementation("org.assertj:assertj-core:3.20.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}