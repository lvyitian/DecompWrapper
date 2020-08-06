package dev.decompwrapper.decompilers.cfr

import dev.decompwrapper.decompilers.DecompiledClass
import dev.decompwrapper.decompilers.Wrapper
import dev.decompwrapper.decompilers.cfr.sink.CFROutputSink
import dev.decompwrapper.decompilers.utils.cfr.createCfrDriver

class CFRWrapper : Wrapper {
    override fun analyseClass(pathToClass: String): DecompiledClass {
        val outputSink = CFROutputSink()

        createCfrDriver(outputSink).analyse(listOf(pathToClass))
        return outputSink.contents[0]
    }

    override fun analyseClasses(pathsToClasses: List<String>): List<DecompiledClass> {
        val outputSink = CFROutputSink()

        createCfrDriver(outputSink).analyse(pathsToClasses)
        return outputSink.contents
    }

    override fun analyseJar(pathToJar: String): List<DecompiledClass> {
        val outputSink = CFROutputSink()

        createCfrDriver(outputSink).analyse(listOf(pathToJar))
        return outputSink.contents
    }
}
