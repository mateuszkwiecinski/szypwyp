package pl.ccki.szypwyp.domain.commands

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import pl.ccki.szypwyp.domain.models.AppId
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.AppOpeningService
import pl.ccki.szypwyp.domain.services.AppsCheckingService
import pl.ccki.szypwyp.domain.services.ExternalPlugin

class OpenExternalAppCommandTest {

    lateinit var appsChecker: AppsCheckingService
    lateinit var appOpener: AppOpeningService
    @Before
    fun setUp() {
        appsChecker = mock()
        appOpener = mock()
    }

    @Test
    fun `should open external app if installed`() {
        val pluginId = Generate(1)
        val plugin = mock<ExternalPlugin> {
            on { appId } doReturn AppId("app")
        }
        appsChecker.stub {
            on { isAppInstalled(any()) } doReturn true
        }
        val command = OpenExternalAppCommand(mapOf(pluginId to plugin), appsChecker, appOpener)

        val test = command.execute(pluginId).test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        verify(appOpener).openApp(argThat { id == "app" })
    }

    @Test
    fun `should open store if not installed`() {
        val pluginId = Generate(1)
        val plugin = mock<ExternalPlugin> {
            on { appId } doReturn AppId("app")
        }
        appsChecker.stub {
            on { isAppInstalled(any()) } doReturn false
        }
        val command = OpenExternalAppCommand(mapOf(pluginId to plugin), appsChecker, appOpener)

        val test = command.execute(pluginId).test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        verify(appOpener).openStore(argThat { id == "app" })
    }
}

private fun Generate(id: Int): PluginId = PluginId(id.toString())
