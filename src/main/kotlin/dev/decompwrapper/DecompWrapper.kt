package dev.decompwrapper

import dev.decompwrapper.decompilers.cfr.CFRWrapper
import dev.decompwrapper.decompilers.jdcore.JDCoreWrapper
import dev.decompwrapper.decompilers.utils.WrapperType
import dev.decompwrapper.ui.DecompWrapperFrame

object DecompWrapper {
    private val decompWrappers = mutableMapOf(
        WrapperType.JDCore to JDCoreWrapper(),
        WrapperType.CFR to CFRWrapper()
    )

    fun getAnalyser(type: WrapperType = WrapperType.CFR) =
        decompWrappers[(decompWrappers.keys.find { it == type } ?: decompWrappers[WrapperType.CFR])]
            ?: error("Could not find wrapper")
}

fun main() {
    val frame = DecompWrapperFrame()
}
