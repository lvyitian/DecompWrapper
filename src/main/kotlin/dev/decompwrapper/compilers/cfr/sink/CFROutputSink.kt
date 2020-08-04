package dev.decompwrapper.compilers.cfr.sink

import org.benf.cfr.reader.api.OutputSinkFactory
import org.benf.cfr.reader.api.OutputSinkFactory.SinkClass
import org.benf.cfr.reader.api.OutputSinkFactory.SinkType
import org.benf.cfr.reader.api.SinkReturns.Decompiled
import java.util.function.Consumer


class CFROutputSink : OutputSinkFactory {
    private val decompiled: MutableMap<String, String> = mutableMapOf()

    override fun getSupportedSinks(
        sinkType: SinkType,
        collection: Collection<SinkClass>
    ): List<SinkClass> = if (sinkType == SinkType.JAVA && collection.contains(SinkClass.DECOMPILED)) {
        listOf(SinkClass.DECOMPILED, SinkClass.STRING)
    } else {
        mutableListOf(SinkClass.STRING)
    }

    private val dumpDecompiled =
        Consumer { d: Decompiled ->
            decompiled[d.packageName + "." + d.className] = d.java
        }

    override fun <T> getSink(sinkType: SinkType, sinkClass: SinkClass): OutputSinkFactory.Sink<T>? =
        if (sinkType == SinkType.JAVA && sinkClass == SinkClass.DECOMPILED) {
            OutputSinkFactory.Sink { dumpDecompiled.accept(it as Decompiled) }
        } else NoopSink()

}