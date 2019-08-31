import org.gradle.plugins.ide.idea.model.IdeaModel

configure<SourceSetContainer> {
	val main by getting
	maybeCreate("integration-test").apply {
		compileClasspath += main.output
		runtimeClasspath += main.output
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

tasks.withType<Test> {
	useJUnitPlatform()
	onOutput(KotlinClosure2<TestDescriptor, TestOutputEvent, Unit>({ _, outputEvent ->
		logger.lifecycle(outputEvent.message.trim())
	}))
}

val integrationTest: SourceSet = the<SourceSetContainer>()["integration-test"]
tasks {
	maybeCreate<Test>("integrationTest").apply {
		useJUnitPlatform()
		testClassesDirs = integrationTest.output.classesDirs
		classpath = integrationTest.runtimeClasspath
	}
}

tasks.withType<JacocoReport> {
	executionData.setFrom(files("${buildDir}/jacoco/test.exec", "${buildDir}/jacoco/integrationTest.exec"))
	reports {
		xml.isEnabled = true
		html.isEnabled = true
	}
}

val idea = the<IdeaModel>()
idea.module {
	testSourceDirs = testSourceDirs +
			integrationTest.java.srcDirs +
			integrationTest.resources.srcDirs
}
