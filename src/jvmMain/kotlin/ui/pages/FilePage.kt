package ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.PageAddDropdownMenu
import ui.pages.file.FileList
import ui.pages.file.FilePageTools
import ui.pages.file.ListForFile
import theme.AppColorsProvider


/**
 * @Description:文件页面
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/6/2
 */
@Composable
fun FilePage() {
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().background(AppColorsProvider.current.card)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(20.dp)
        ) {
            FilePageTools()
            ListForFile()
            FileList()
        }
    }
}

/**
 * 添加按钮的下拉菜单项
 */
@Composable
fun PageDropdownMenuItem(pageAddDropdownMenu: PageAddDropdownMenu) {
    DropdownMenuItem(onClick = { }) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                pageAddDropdownMenu.icon,
                contentDescription = pageAddDropdownMenu.title,
                tint = AppColorsProvider.current.firstIcon
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = pageAddDropdownMenu.title, color = AppColorsProvider.current.firstText)
        }
    }
}

