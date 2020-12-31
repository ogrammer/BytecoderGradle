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

## License

The plugin itself uses the GPLv3 license, however it also has the Classpath Exception (from the JDK) which allows you to use the library and even embed it in a project with any license. Any changes made to the plugin itself must be open-source.

Note: this is just a summary and shouldn't be used for legal purposes, read the [LICENSE](LICENSE) file for more information.