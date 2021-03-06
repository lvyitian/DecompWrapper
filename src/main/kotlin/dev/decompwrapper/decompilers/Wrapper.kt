package dev.decompwrapper.decompilers

interface Wrapper {
    fun analyseClass(pathToClass: String): DecompiledClass
    fun analyseClasses(pathsToClasses: List<String>): List<DecompiledClass>

    fun analyseJar(pathToJar: String): List<DecompiledClass>
}
