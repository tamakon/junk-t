import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.7.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.41"
	kotlin("plugin.spring") version "1.3.41"
	jacoco
}

group = "com"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val developmentOnly: Configuration  by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.0")
	implementation("io.springfox:springfox-swagger2:2.9.2")
	implementation("org.springframework.security.oauth:spring-security-oauth2:2.3.6.RELEASE")
	implementation("org.flywaydb:flyway-core")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("mysql:mysql-connector-java")
	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "junit")
		exclude(module = "mockito-core")
	}
	testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:2.1.0")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.3")
	testImplementation("io.kotlintest:kotlintest-extensions-spring:3.3.3")
	testImplementation("com.ninja-squad:springmockk:1.1.2")
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = java.sourceCompatibility.toString()
	}
}

apply(from = "test.gradle.kts")
