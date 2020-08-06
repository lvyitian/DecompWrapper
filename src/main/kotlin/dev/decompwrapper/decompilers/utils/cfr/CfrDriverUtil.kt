package dev.decompwrapper.decompilers.utils.cfr

import org.benf.cfr.reader.api.CfrDriver
import org.benf.cfr.reader.api.OutputSinkFactory

fun createCfrDriver(outputSinkFactory: OutputSinkFactory): CfrDriver = CfrDriver.Builder().withOutputSink(outputSinkFactory).build()