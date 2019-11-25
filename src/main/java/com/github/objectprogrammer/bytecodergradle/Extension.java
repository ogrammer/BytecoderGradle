package com.github.objectprogrammer.bytecodergradle;

import de.mirkosertic.bytecoder.allocator.Allocator;
import de.mirkosertic.bytecoder.backend.CompileOptions;
import de.mirkosertic.bytecoder.backend.CompileTarget;
import de.mirkosertic.bytecoder.optimizer.KnownOptimizer;
import de.mirkosertic.bytecoder.unittest.Slf4JLogger;

@SuppressWarnings("WeakerAccess")
public class Extension {

	public String buildDirectory = "bytecoder";
	public String mainClass;
	public CompileTarget.BackendType backend = CompileTarget.BackendType.js;
	public boolean debugOutput = false;
	public boolean exceptionHandling = false;
	public KnownOptimizer optimizer = KnownOptimizer.ALL;
	public String filenamePrefix = "bytecoder";
	public int wasmMinimumPages = 512;
	public int wasmMaximumPages = 1024;
	public boolean minify = true;
	public boolean preferStackifier = false;
	public Allocator registerAllocator = Allocator.linear;
	public String[] additionalClassesToLink = {};
	public String[] additionalResources = {};

	public CompileOptions toCompileOptions() {
		return new CompileOptions(new Slf4JLogger(), debugOutput, optimizer, exceptionHandling, filenamePrefix, wasmMinimumPages, wasmMaximumPages, minify, preferStackifier, registerAllocator, additionalClassesToLink, additionalResources);
	}
}
