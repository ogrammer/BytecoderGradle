# BytecoderGradle

## Example

```groovy
buildscript {
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
    dependencies {
        classpath group: 'de.mirkosertic.bytecoder', name: 'bytecoder-core', version: '2019-08-30'
        classpath group: 'com.github.objectprogrammer', name: 'bytecodergradle', version: '0.0.1'
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

sourceCompatibility = 1.8

repositories {
    mavenCentral()
}

dependencies {
    compile group: 'de.mirkosertic.bytecoder', name: 'bytecoder-core', version: '2019-08-30'
}
```

## All options with their default values

mainClass (String) is the only required option.

```groovy
bytecoder {
    buildDirectory = 'bytecoder'
    backend = 'js' // or 'wasm'
    debugOutput = false
    exceptionHandling = false
    optimizer = 'ALL'
    filenamePrefix = 'bytecoder'
    wasmMinimumPages = 512
    wasmMaximumPages = 1024
    minify = true
    preferStackifier = false
}
```
