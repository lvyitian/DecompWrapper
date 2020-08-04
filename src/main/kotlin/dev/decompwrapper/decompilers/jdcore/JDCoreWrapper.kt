package dev.decompwrapper.decompilers.jdcore

import dev.decompwrapper.decompilers.DecompiledClass
import dev.decompwrapper.decompilers.Wrapper
import dev.decompwrapper.decompilers.jdcore.loader.JDCoreLoader
import dev.decompwrapper.decompilers.jdcore.printer.JDCorePrinter
import org.jd.core.v1.ClassFileToJavaSourceDecompiler
import java.io.File
import kotlin.Exception

class JDCoreWrapper : Wrapper {
    private val loader = JDCoreLoader()
    private val decompiler = ClassFileToJavaSourceDecompiler()
    private val printer = JDCorePrinter()

    override fun analyseClass(pathToClass: String): DecompiledClass {
        decompiler.decompile(loader, printer, pathToClass)
        return try {
            DecompiledClass(pathToClass.split(File.separatorChar)[1].removeSuffix(".class"), printer.toString())
        } catch (e: Exception) {
            error("Please use File.separatorChar instead of a self defined char")
        }
    }

    override fun analyseClasses(pathsToClasses: List<String>): List<DecompiledClass> {
        val classes = mutableListOf<DecompiledClass>()
        pathsToClasses.forEach {
            decompiler.decompile(loader, printer, it)
            classes.add(DecompiledClass((it.split(File.separatorChar)[1].removeSuffix(".class")), printer.toString()))
        }
        return classes
    }
}