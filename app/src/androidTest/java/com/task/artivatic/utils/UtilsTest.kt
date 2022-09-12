package com.task.artivatic.utils

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.task.artivatic.ui.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UtilsTest {

    @Test
    fun happyCase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = Utils.isNetworkAvailable(context)
        assertThat(result).isTrue()
    }
}