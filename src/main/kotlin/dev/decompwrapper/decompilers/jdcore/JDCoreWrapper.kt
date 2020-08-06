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
        val path = pathToClass.split(File.separatorChar)
        decompiler.decompile(loader, printer, pathToClass)
        return try {
            DecompiledClass(path[path.size - 1].removeSuffix(".class"), printer.toString())
        } catch (e: Exception) {
            error("Please use File.separatorChar instead of a self defined char")
        }
    }

    override fun analyseClasses(pathsToClasses: List<String>): List<DecompiledClass> {
        val classes = mutableListOf<DecompiledClass>()
        pathsToClasses.forEach {
            val path = it.split(File.separatorChar)
            decompiler.decompile(loader, printer, it)
            classes.add(DecompiledClass(path[path.size - 1].removeSuffix(".class"), printer.toString()))
        }
        return classes
    }
}
