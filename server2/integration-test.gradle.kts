configure<SourceSetContainer> {
	val main by getting
	val test by getting
	maybeCreate("integrationTest").apply {
		compileClasspath += main.output + test.output
		runtimeClasspath += main.output+ test.output
		java {
			srcDir("src/integration-test/kotlin")
		}
		resources {
			srcDir("src/integration-test/resources")
		}
	}
}

configurations {
	val testImplementation by getting
	val testRuntimeOnly by getting
	named("integrationTestImplementation") {
		extendsFrom(testImplementation)
	}
	named("integrationTestRuntimeOnly") {
		extendsFrom(testRuntimeOnly)
	}
}

val integrationTest: SourceSet = the<SourceSetContainer>()["integrationTest"]
tasks {
	maybeCreate<Test>("integrationTest").apply {
		useJUnitPlatform()
		testClassesDirs = integrationTest.output.classesDirs
		classpath = integrationTest.runtimeClasspath
	}
}