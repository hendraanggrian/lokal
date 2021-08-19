package com.hendraanggrian.prefs.jvm

import com.hendraanggrian.prefs.PreferencesLogger
import com.hendraanggrian.prefs.Prefs
import java.io.File
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PropertiesPreferencesTest {
    private lateinit var preferences: PropertiesPreferences

    @BeforeTest
    fun createTest() {
        Prefs.setLogger(PreferencesLogger.System)
        preferences = Prefs[File("test.properties").apply { if (exists()) delete() }]
    }

    @Test
    fun properties() {
        preferences["name"] = "Hendra"
        preferences.save()
        assertEquals("Hendra", preferences["name"])
    }
}