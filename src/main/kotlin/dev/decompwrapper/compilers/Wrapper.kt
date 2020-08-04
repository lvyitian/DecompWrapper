package dev.decompwrapper.compilers

interface Wrapper {
    fun analyseClass(pathToClass: String) : String

    fun analyseClasses(pathsToClasses: List<String>): List<String>
}