plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("com.diffplug.spotless") version "6.25.0"
}

group = "com.declub"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

allprojects {
    repositories {
        mavenCentral()
    }
    plugins.apply("com.diffplug.spotless")
    spotless {
        java {
            importOrder()
            cleanthat()
            removeUnusedImports()
            googleJavaFormat()
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

subprojects {
    plugins.apply {
        apply("java")
        apply("com.github.johnrengelman.shadow")
    }

    dependencies {
        // data faker
        implementation("net.datafaker:datafaker:2.4.2")

        // logger
        implementation("org.slf4j:slf4j-jdk14:1.7.32")
        implementation("ch.qos.logback:logback-classic:1.4.12")
        // flink 프로젝트인 경우 flink 의존성
        when (project.name) {
            "flink" -> {
                // flink 버전 명시
                val flinkVersion = "1.19.1"
                println("Implementation flink dependencies...")
                // flink
                implementation("org.apache.flink:flink-streaming-java:$flinkVersion")
                implementation("org.apache.flink:flink-clients:$flinkVersion")
                implementation("org.apache.flink:flink-runtime-web:$flinkVersion")
                implementation("org.apache.flink:flink-avro:$flinkVersion")
                if (project.parent?.name != "common") {
                    implementation(project(":common:flink"))
                }
            }

            "beam" -> {
                // beam 버전 명시
                val beamVersion = "2.62.0"
                println("Implementation beam dependencies...")
                // beam bom
                implementation(platform("org.apache.beam:beam-sdks-java-google-cloud-platform-bom:$beamVersion"))
                beamImplementation(
                    "beam-sdks-java-core",
                    "beam-runners-direct-java",
                )

                beamRuntimeOnly(
                    "beam-sdks-java-io-google-cloud-platform",
                    "beam-runners-google-cloud-dataflow-java",
                    "beam-runners-flink-1.18",
                )

                // auto value
                compileOnly("com.google.auto.value:auto-value-annotations")
                annotationProcessor("com.google.auto.value:auto-value:1.10.4")

                if (project.parent?.name != "common") {
                    implementation(project(":common:beam"))
                }
            }
        }
    }

    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }
}

tasks.test {
    useJUnitPlatform()
}

fun DependencyHandlerScope.beamImplementation(vararg args: String) {
    for (arg in args) {
        implementation("org.apache.beam:$arg")
    }
}

fun DependencyHandlerScope.beamRuntimeOnly(vararg args: String) {
    for (arg in args) {
        runtimeOnly("org.apache.beam:$arg")
    }
}
