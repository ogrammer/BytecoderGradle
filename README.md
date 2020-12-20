# BytecoderGradle

## Example

```groovy
buildscript {
	repositories {
		mavenCentral()
		maven { url 'https://jitpack.io' }
	}
	dependencies {
		classpath group: 'de.mirkosertic.bytecoder', name: 'bytecoder-core', version: '2020-12-01'
		classpath group: 'com.github.objectprogrammer', name: 'bytecodergradle', version: '2020-12-01'
	}
}

apply plugin: 'java'
apply plugin: 'com.github.objectprogrammer.bytecodergradle'

bytecoder {
	mainClass = 'com.example.Main'
	backend = 'wasm'
}

group 'com.example'
version '1.0.0'

repositories {
	mavenCentral()
}

dependencies {
	compile group: 'de.mirkosertic.bytecoder', name: 'bytecoder-core', version: '2020-12-01'
}
```

## All options with their default values

**mainClass** (String) is the only required option.

```groovy
bytecoder {
	mainClass = 'com.example.Main'
	buildDirectory = 'bytecoder'
	backend = 'js' // or 'wasm' or 'wasm_llvm'
	debugOutput = false
	exceptionHandling = false
	optimizer = 'ALL'
	filenamePrefix = 'bytecoder'
	wasmInitialPages = 512
	wasmMaximumPages = 1024
	minify = true
	preferStackifier = false
	registerAllocator = 'linear' // or 'passthru'
	additionalClassesToLink = []
	additionalResources = []
	llvmOptimizationLevel = 'O2'
	escapeAnalysis = false
}
```
