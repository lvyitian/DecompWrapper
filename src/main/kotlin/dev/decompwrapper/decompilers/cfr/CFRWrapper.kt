package dev.decompwrapper.decompilers.cfr

import dev.decompwrapper.decompilers.Wrapper
import dev.decompwrapper.decompilers.cfr.sink.CFROutputSink
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