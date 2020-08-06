package dev.decompwrapper.decompilers.jdcore.loader

import org.jd.core.v1.api.loader.Loader
import java.io.File

class JDCoreLoader : Loader {
    override fun canLoad(internalName: String) = File(internalName).exists()

    override fun load(internalName: String) = File(internalName).readBytes()
}
