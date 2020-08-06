package dev.decompwrapper.decompilers.cfr.sink

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
    ): List<SinkClass> {
        return if (sinkType == SinkType.JAVA && collection.contains(SinkClass.DECOMPILED)) {
            listOf(SinkClass.DECOMPILED, SinkClass.STRING)
        } else listOf(SinkClass.STRING)
    }

    private val dumpDecompiled = Consumer { d: Decompiled -> decompiled[d.packageName + "." + d.className] = d.java }

    override fun <T> getSink(sinkType: SinkType, sinkClass: SinkClass): OutputSinkFactory.Sink<T>? {
        return if (sinkType == SinkType.JAVA && sinkClass == SinkClass.DECOMPILED) {
            OutputSinkFactory.Sink { dumpDecompiled.accept(it as Decompiled) }
        } else NoopSink()
    }
}
