package dev.decompwrapper

import dev.decompwrapper.decompilers.cfr.CFRWrapper
import dev.decompwrapper.decompilers.jdcore.JDCoreWrapper
import dev.decompwrapper.decompilers.utils.WrapperType

object DecompWrapper {
    private val decompWrappers = mutableMapOf(
        WrapperType.JDCore to JDCoreWrapper(),
        WrapperType.CFR to CFRWrapper()
    )

    fun getAnalyser(type: WrapperType = WrapperType.CFR)
            = decompWrappers[(decompWrappers.keys.find { it == type } ?: error("Could not find Wrapper"))] ?: error("Could not find wrapper")
}