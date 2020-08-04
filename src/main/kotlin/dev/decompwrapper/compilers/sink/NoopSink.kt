package dev.decompwrapper.compilers.sink

import org.benf.cfr.reader.api.OutputSinkFactory

class NoopSink<T> : OutputSinkFactory.Sink<T> {
    override fun write(sinkable: T) {
        // Do absolutely nothing
    }
}
