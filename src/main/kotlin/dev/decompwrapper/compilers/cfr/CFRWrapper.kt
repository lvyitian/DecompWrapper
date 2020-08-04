package dev.decompwrapper.compilers.cfr

import dev.decompwrapper.compilers.Wrapper
import dev.decompwrapper.compilers.cfr.sink.CFROutputSink
import org.benf.cfr.reader.api.CfrDriver

class CFRWrapper : Wrapper {
    private val driver: CfrDriver = CfrDriver.Builder()
        .withOutputSink(CFROutputSink())
        .build()

    override fun analyseClass(pathToClass: String): String {
        driver.analyse(listOf(pathToClass))
        TODO("Not yet implemented")
    }

    override fun analyseClasses(pathsToClasses: List<String>): List<String> {
        driver.analyse(pathsToClasses)
        TODO("Not yet implemented")
    }
}