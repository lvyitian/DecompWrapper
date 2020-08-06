package dev.decompwrapper.decompilers.cfr

import dev.decompwrapper.decompilers.DecompiledClass
import dev.decompwrapper.decompilers.Wrapper
import dev.decompwrapper.decompilers.cfr.sink.CFROutputSink
import org.benf.cfr.reader.api.CfrDriver

class CFRWrapper : Wrapper {
    override fun analyseClass(pathToClass: String): DecompiledClass {
        val outputSink = CFROutputSink()

        CfrDriver.Builder().withOutputSink(outputSink).build().analyse(listOf(pathToClass))
        return outputSink.contents[0]
    }

    override fun analyseClasses(pathsToClasses: List<String>): List<DecompiledClass> {
        val outputSink = CFROutputSink()

        CfrDriver.Builder().withOutputSink(outputSink).build().analyse(pathsToClasses)
        return outputSink.contents
    }

    override fun analyseJar(pathToJar: String): List<DecompiledClass> {
        val outputSink = CFROutputSink()

        CfrDriver.Builder().withOutputSink(outputSink).build().analyse(listOf(pathToJar))
        return outputSink.contents
    }
}
