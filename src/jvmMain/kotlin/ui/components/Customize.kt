package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import model.PageAddDropdownMenu
import theme.AppColorsProvider
import ui.pages.PageDropdownMenuItem


/**
 * @Description:自定义组件类
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/7/28
 */

//自定义下拉菜单样式
@Composable
fun CustomizeDropdownMenu(
    menuList: List<PageAddDropdownMenu>,
    expanded: Boolean,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        modifier = Modifier.border(
            width = 10.dp,
            color = AppColorsProvider.current.card,
            shape = RoundedCornerShape(10.dp)
        ).background(AppColorsProvider.current.card),
        expanded = expanded,
        offset = DpOffset(40.dp, (-20).dp),
        onDismissRequest = onDismissRequest
    ) {
        //更多操作菜单项
        menuList.forEach { pageAddDropdownMenu ->
            PageDropdownMenuItem(pageAddDropdownMenu)
        }
    }
}

