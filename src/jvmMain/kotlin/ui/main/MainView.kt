package ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.main.layout.LeftMenu
import ui.main.layout.RightContainer


/**
 * @Description:主页面
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/6/1
 */
@Composable
fun MainView() {
    Column {
        Box(
            modifier = Modifier.weight(1f),
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                LeftMenu()
                RightContainer()
            }
        }
    }
}