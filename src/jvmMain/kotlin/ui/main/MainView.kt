package ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.main.layout.MainLeftMenu


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
        Box(modifier = Modifier.weight(1f)){
            Row(modifier = Modifier.fillMaxSize()) {
                MainLeftMenu()
//                MainRightContainer()
            }
        }
    }
}