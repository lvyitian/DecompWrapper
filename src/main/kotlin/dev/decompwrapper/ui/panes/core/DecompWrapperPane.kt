package dev.decompwrapper.ui.panes.core

import dev.decompwrapper.ui.theme.DecompWrapperTheme
import javax.swing.BoxLayout
import javax.swing.JPanel

open class DecompWrapperPane: JPanel() {
    init {
        background = DecompWrapperTheme.backgroundColor
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
    }
}
