package dev.decompwrapper.decompilers.jdcore

import dev.decompwrapper.decompilers.DecompiledClass
import dev.decompwrapper.decompilers.Wrapper
import dev.decompwrapper.decompilers.jdcore.loader.JDCoreLoader
import dev.decompwrapper.decompilers.jdcore.printer.JDCorePrinter
import org.jd.core.v1.ClassFileToJavaSourceDecompiler
import java.io.File

class JDCoreWrapper : Wrapper {
    private val loader = JDCoreLoader()
    private val decompiler = ClassFileToJavaSourceDecompiler()
    private val printer = JDCorePrinter()

    override fun analyseClass(pathToClass: String): DecompiledClass {
        val path = pathToClass.split(pathToClass.getSeparator())
        decompiler.decompile(loader, printer, pathToClass)
        return DecompiledClass("", path[path.size - 1].removeSuffix(".class"), printer.toString())
    }

    override fun analyseClasses(pathsToClasses: List<String>): List<DecompiledClass> {
        val classes = mutableListOf<DecompiledClass>()
        pathsToClasses.forEach {
            val path = it.split(it.getSeparator())
            decompiler.decompile(loader, printer, it)
            classes.add(DecompiledClass("", path[path.size - 1].removeSuffix(".class"), printer.toString()))
        }
        return classes
    }

    override fun analyseJar(pathToJar: String): List<DecompiledClass> {
        TODO("Not implemented yet")
    }
}

fun File.getSeparator() = when {
    path.contains("\\") -> "\\"
    else -> "/["
}

fun String.getSeparator() = when {
    contains("\\") -> "\\"
    else -> "/"
}