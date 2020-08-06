package dev.decompwrapper.ui.panes

import dev.decompwrapper.ui.panes.core.DecompWrapperPane
import dev.decompwrapper.ui.theme.DecompWrapperTheme
import java.awt.Component
import javax.swing.JLabel

class DecompilingPane: DecompWrapperPane() {
    init {
        val label = JLabel("Decompiling...", JLabel.CENTER)
        label.foreground = DecompWrapperTheme.foregroundColor
        label.alignmentX = Component.CENTER_ALIGNMENT

        add(label)
    }
}
