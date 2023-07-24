package ui.main.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import base.AppConfig
import router.CDGraph
import ui.theme.AppColorsProvider


/**
 * @Description:
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/6/2
 */
@Composable
fun RightContainer() {
    Box(modifier = Modifier.fillMaxSize().background(color = AppColorsProvider.current.pure)) {
        Spacer(
            modifier = Modifier.fillMaxWidth().height(AppConfig.topBarHeight)
                .background(AppColorsProvider.current.topBarColor)
        )
        CDGraph()
    }
}