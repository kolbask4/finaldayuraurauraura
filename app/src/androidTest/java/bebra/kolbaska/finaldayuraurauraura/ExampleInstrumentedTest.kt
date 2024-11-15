package bebra.kolbaska.finaldayuraurauraura

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("bebra.kolbaska.finaldayuraurauraura", appContext.packageName)
    }
}

@RunWith(AndroidJUnit4::class)
class AppUITesting{
    @Test
    fun startTesting() {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        device.pressHome()
        val appName: UiObject2 = device.findObject(By.text("finaldayuraurauraura"))
        appName.clickAndWait(Until.newWindow(), 3000)

        val scrollable = device.findObject(By.scrollable(true))
        scrollable.fling(Direction.UP)
        Thread.sleep(1000)
        val someElement = device.findObject(By.text("L'Appart à Méribel"))
        assertTrue(someElement.isEnabled)


    }
}