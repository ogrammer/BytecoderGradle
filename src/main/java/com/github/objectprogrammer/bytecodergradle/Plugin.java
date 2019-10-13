package com.github.objectprogrammer.bytecodergradle;

import org.gradle.api.Project;

public class Plugin implements org.gradle.api.Plugin<Project> {
	@Override
	public void apply(Project project) {
		project.getTasks().register("bytecoder", Task.class).configure(t -> t.dependsOn("classes"));
		project.getExtensions().create("bytecoder", Extension.class);
	}
}
