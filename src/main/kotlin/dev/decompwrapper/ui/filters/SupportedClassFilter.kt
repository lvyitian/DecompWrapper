package dev.decompwrapper.ui.filters

import dev.decompwrapper.ui.utils.supportedExtensions
import java.io.File
import javax.swing.filechooser.FileFilter

object SupportedClassFilter : FileFilter() {
    override fun accept(f: File?) = f?.extension in supportedExtensions

    override fun getDescription() = ".jar & .class files"
}