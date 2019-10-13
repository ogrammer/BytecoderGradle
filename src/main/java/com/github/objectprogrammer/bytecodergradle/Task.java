package com.github.objectprogrammer.bytecodergradle;

import de.mirkosertic.bytecoder.backend.CompileResult;
import de.mirkosertic.bytecoder.backend.CompileTarget;
import de.mirkosertic.bytecoder.core.*;
import org.gradle.api.DefaultTask;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task extends DefaultTask {

	@TaskAction
	public void execute() throws TaskExecutionException {
		Extension extension = (Extension) getProject().getExtensions().getByName("bytecoder");
		Objects.requireNonNull(extension.mainClass, "mainClass was not provided");
		File bytecoderDir = new File(getProject().getBuildDir(), extension.buildDirectory);
		bytecoderDir.mkdirs();
		try {
			ClassLoader loader = createClassLoader();
			Class mainClass = loader.loadClass(extension.mainClass);
			CompileTarget compileTarget = new CompileTarget(loader, extension.backend);
			BytecodeMethodSignature signature = new BytecodeMethodSignature(BytecodePrimitiveTypeRef.VOID,
					new BytecodeTypeRef[] { new BytecodeArrayTypeRef(BytecodeObjectTypeRef.fromRuntimeClass(String.class), 1) });
			CompileResult code = compileTarget.compile(extension.toCompileOptions(), mainClass, "main", signature);
			for (CompileResult.Content content : code.getContent()) {
				File fileName = new File(bytecoderDir, content.getFileName());
				try (FileOutputStream fos = new FileOutputStream(fileName)) {
					content.writeTo(fos);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			throw new TaskExecutionException(this, e);
		}
	}

	protected final ClassLoader createClassLoader() throws TaskExecutionException {
		List<URL> urls = new ArrayList<>();
		SourceSetContainer sourceSets = getProject().getConvention().getPlugin(JavaPluginConvention.class).getSourceSets();
		try {
			for (SourceSet sourceSet : sourceSets) {
				for (File file : sourceSet.getCompileClasspath()) {
					urls.add(file.toURI().toURL());
				}
				for (File classesDir : sourceSet.getOutput().getClassesDirs()) {
						urls.add(classesDir.toURI().toURL());
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new TaskExecutionException(this, e);
		}
		return new URLClassLoader(urls.toArray(new URL[0]), getClass().getClassLoader());
	}
}
