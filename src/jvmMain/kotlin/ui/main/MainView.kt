package ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.main.layout.MainLeftMenu
import ui.main.layout.MainRightContainer
import ui.theme.AppColorsProvider


/**
 * @Description:主页面
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/6/1
 */
@Composable
fun MainView() {
    Column{
        Box(
            modifier = Modifier.weight(1f),
        ){
            Row(modifier = Modifier.fillMaxSize()) {
                MainLeftMenu()
                MainRightContainer()
            }
        }
    }
}