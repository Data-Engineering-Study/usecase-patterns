plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("com.diffplug.spotless") version "6.25.0"
}

group = "com.declub"
version = "1.0-SNAPSHOT"

allprojects {
    plugins.apply("com.diffplug.spotless")
    spotless {
        java {
            importOrder()
            cleanthat()
            removeUnusedImports()
            googleJavaFormat()
        }
        kotlin {
            ktfmt()
            ktlint()
        }
        kotlinGradle {
            target("*.gradle.kts")
            ktlint()
        }
        yaml {
            target("src/**/*.yaml", "src/**/*.yml")
            jackson()
            prettier()
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
