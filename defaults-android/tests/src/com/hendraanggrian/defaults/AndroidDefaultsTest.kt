package com.hendraanggrian.defaults

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.test.InstrumentationRegistry
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
@LargeTest
class AndroidDefaultsTest {

    private lateinit var context: Context
    private lateinit var testPreferences: SharedPreferences

    @Before
    fun createTest() {
        context = InstrumentationRegistry.getContext()
        testPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        testPreferences.edit().clear().commit()
    }

    @Test
    fun sharedPreferences() {
        val defaults = testPreferences.toDefaults()
        defaults.invoke {
            it["name"] = "Hendra"
            it["age"] = 25
        }
        assertEquals("Hendra", defaults["name"])
        assertEquals(25, defaults.getInt("age"))
    }

    @Test
    fun context() {
        val defaults = context.toDefaults()
        defaults {
            it["name"] = "Hendra"
            it["age"] = 25
        }
        assertEquals("Hendra", defaults["name"])
        assertEquals(25, defaults.getInt("age"))
    }
}