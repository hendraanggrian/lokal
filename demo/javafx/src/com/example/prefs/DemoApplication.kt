package com.example.prefs

import com.hendraanggrian.prefs.BindPref
import com.hendraanggrian.prefs.Prefs
import com.hendraanggrian.prefs.PrefsSaver
import com.hendraanggrian.prefs.jvm.safeBind
import com.hendraanggrian.prefs.jvm.setDebug
import javafx.application.Application
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import javafx.stage.Stage
import ktfx.controls.gap
import ktfx.controls.paddingAll
import ktfx.coroutines.onAction
import ktfx.dialogs.infoAlert
import ktfx.launchApplication
import ktfx.layouts.button
import ktfx.layouts.buttonBar
import ktfx.layouts.checkBox
import ktfx.layouts.gridPane
import ktfx.layouts.label
import ktfx.layouts.scene
import ktfx.layouts.textField
import org.apache.commons.lang3.SystemUtils
import java.io.File

class DemoApplication : Application() {

    companion object {
        @JvmStatic fun main(args: Array<String>) = launchApplication<DemoApplication>(*args)
    }

    private lateinit var nameField: TextField
    private lateinit var marriedCheck: CheckBox
    private lateinit var ageField: TextField
    private lateinit var heightField: TextField

    @BindPref @JvmField var name: String? = null
    @BindPref @JvmField var married: Boolean = false
    @BindPref @JvmField var age: Int = 0
    @BindPref @JvmField var height: Double = 0.0

    private lateinit var saver: PrefsSaver

    override fun init() {
        Prefs.setDebug(true)
    }

    override fun start(stage: Stage) {
        saver =
            Prefs.safeBind(File(SystemUtils.USER_HOME, "Desktop").resolve("test.properties"), this)
        stage.run {
            scene {
                gridPane {
                    paddingAll = 10.0
                    gap = 10.0
                    label("Name") row 0 col 0
                    nameField = textField(name.orEmpty()) row 0 col 1
                    label("Married") row 1 col 0
                    marriedCheck = checkBox { isSelected = married } row 1 col 1
                    label("Age") row 2 col 0
                    ageField = textField(age.toString()) row 2 col 1
                    label("Height") row 3 col 0
                    heightField = textField(this@DemoApplication.height.toString()) row 3 col 1
                    buttonBar {
                        button("Save") {
                            onAction {
                                name = nameField.text
                                married = marriedCheck.isSelected
                                age = ageField.text.toInt()
                                this@DemoApplication.height = heightField.text.toDouble()
                                saver.saveAsync()
                                infoAlert("Saved!")
                            }
                        }
                    } row 4 col (0 to 2)
                }
            }
            show()
        }
    }
}