package dev.decompwrapper.ui

import dev.decompwrapper.DecompWrapper
import dev.decompwrapper.ui.panes.DecompilingPane
import dev.decompwrapper.ui.panes.MainPane
import dev.decompwrapper.ui.panes.core.DecompWrapperPane
import dev.decompwrapper.ui.theme.DecompWrapperTheme
import java.awt.*
import java.awt.event.KeyEvent
import java.io.File
import javax.swing.JDialog
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.UIManager


class DecompWrapperFrame : JFrame() {
    var pane: DecompWrapperPane = MainPane()

    init {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        setDefaultLookAndFeelDecorated(true)

        val screenSize = Toolkit.getDefaultToolkit().screenSize

        setSize(screenSize.width / 2, screenSize.height / 2)

        updatePane(pane)
        makeMenuBar()

        title = "DecompWrapper"
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }

    fun updatePane(pane: DecompWrapperPane) {
        this.pane = pane
        contentPane = this.pane

        invalidate()
        revalidate()
    }

    private fun makeMenuBar() {
        val menuBar = MenuBar()

        val fileMenu = Menu("File")

        val openItem = MenuItem("Open JAR or class file")
        openItem.shortcut = MenuShortcut(KeyEvent.VK_O)
        openItem.addActionListener {
            openFile()
        }

        val exportItem = MenuItem("Export")
        exportItem.isEnabled = false

        fileMenu.add(openItem)
        fileMenu.add(exportItem)

        menuBar.add(fileMenu)
        setMenuBar(menuBar)
    }

    private fun openFile() {
        val fileChooser = JFileChooser()
        fileChooser.currentDirectory = File(System.getProperty("user.dir"))

        val result = fileChooser.showOpenDialog(this)
        if (result == JFileChooser.APPROVE_OPTION) {
            val selectedFile = fileChooser.selectedFile

            val dialogFrame = JFrame()
            dialogFrame.setSize(400, 300)
            dialogFrame.contentPane = DecompilingPane()
            dialogFrame.setLocationRelativeTo(this)

            dialogFrame.isVisible = true
            Thread().run {
                DecompWrapper.getAnalyser().analyseJar(selectedFile.absolutePath)

                dialogFrame.dispose()
            }
        }
    }
}
