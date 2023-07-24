import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import base.AppConfig
import moe.tlaster.precompose.PreComposeWindow
import moe.tlaster.precompose.navigation.rememberNavigator
import org.succlz123.lib.imageloader.core.ImageLoader
import router.CDNavigatorManager
import ui.main.MainView
import ui.theme.AppTheme
import ui.theme.themeTypeState
import utils.EnvUtil
import java.awt.Dimension
import java.io.File


fun main() = application {
    initImageLoader()
    //设置窗口最小尺寸以及初始位置
    val windowState = rememberWindowState(
        size = DpSize(AppConfig.windowMinWidth, AppConfig.windowMinHeight), position = WindowPosition.Aligned(
            Alignment.Center
        )
    )
    PreComposeWindow(
        state = windowState,
        onCloseRequest = ::exitApplication,
        undecorated = EnvUtil.isLinux(),
        icon = painterResource("icons/app/app.svg"),
        title = ""
    ) {
        //设置最小尺寸
        window.minimumSize = Dimension(AppConfig.windowMinWidth.value.toInt(), AppConfig.windowMinHeight.value.toInt())
        //设置主窗口只保留关闭、最小化、最大化系统操作
        window.rootPane.apply {
            rootPane.putClientProperty("apple.awt.fullWindowContent", true)
            rootPane.putClientProperty("apple.awt.transparentTitleBar", true)
            rootPane.putClientProperty("apple.awt.windowTitleVisible", false)
        }
        App()
    }
}

@Composable
@Preview
fun App() {
    AppTheme(themeTypeState.value) {
        CDNavigatorManager.navigator = rememberNavigator()
        MainView()
    }
}

private fun initImageLoader() {
    ImageLoader.configuration(rootDirectory = File(AppConfig.cacheRootDir))
}


