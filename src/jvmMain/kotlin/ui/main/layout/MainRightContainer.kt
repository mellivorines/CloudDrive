package ui.main.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
fun MainRightContainer() {
    Box(modifier = Modifier.fillMaxSize().background(color = AppColorsProvider.current.pure)) {
        Spacer(
            modifier = Modifier.fillMaxWidth().height(50.dp)
                .background(AppColorsProvider.current.topBarColor)
        )
        CDGraph()
    }
}