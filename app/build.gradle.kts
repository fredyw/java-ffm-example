plugins {
    application
    id("com.diffplug.spotless") version "7.0.2"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

application {
    mainClass = "org.example.FfmExample"
}

spotless {
    java {
        googleJavaFormat()
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    jvmArgs("--enable-native-access=ALL-UNNAMED")
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs("--enable-native-access=ALL-UNNAMED")
}

tasks.named("check") {
    dependsOn("spotlessApply")
}
