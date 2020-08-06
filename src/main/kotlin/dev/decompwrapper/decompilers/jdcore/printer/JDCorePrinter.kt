package dev.decompwrapper.decompilers.jdcore.printer

import org.jd.core.v1.api.printer.Printer

class JDCorePrinter : Printer {
    private val tab = "  "
    private val newLine = "\n"

    private var indentationCount = 0
    private var stringBuilder = StringBuilder()

    override fun indent() {
        indentationCount++
    }

    override fun printDeclaration(type: Int, internalTypeName: String, name: String, descriptor: String?) {
        stringBuilder.append(name)
    }

    override fun startLine(lineNumber: Int) {
        for (i in 0..indentationCount) {
            stringBuilder.append(tab)
        }
    }

    override fun printText(text: String) {
        stringBuilder.append(text)
    }

    override fun startMarker(type: Int) {
    }

    override fun unindent() {
        indentationCount--
    }

    override fun end() {
    }

    override fun printNumericConstant(constant: String) {
        stringBuilder.append(constant)
    }

    override fun printKeyword(keyword: String) {
        stringBuilder.append(keyword)
    }

    override fun start(maxLineNumber: Int, majorVersion: Int, minorVersion: Int) {
    }

    override fun printReference(
        type: Int,
        internalTypeName: String,
        name: String,
        descriptor: String?,
        ownerInternalName: String?
    ) {
        stringBuilder.append(name)
    }

    override fun printStringConstant(constant: String, ownerInternalName: String) {
        stringBuilder.append(constant)
    }

    override fun endLine() {
        stringBuilder.append(newLine)
    }

    override fun extraLine(count: Int) {
        var varCount = count
        while (varCount-- > 0) {
            stringBuilder.append(newLine)
        }
    }

    override fun endMarker(type: Int) {
    }

    override fun toString() = stringBuilder.toString()
}
