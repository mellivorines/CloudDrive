package ui.pages.file

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import model.CDMenuInfos
import theme.AppColorsProvider
import ui.pages.PageDropdownMenuItem


/**
 * @Description:文件页面工具栏
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/6/4
 */
@Composable
fun FilePageTools() {
    val cdPageAddDropdownMenu = CDMenuInfos.PAGE_ADD_DROPDOWN_MENU
    val currentPath = remember { mutableStateListOf("文件", "ada", "duahda", "ufgasd") }

    Row(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 文件夹面包屑
        FolderBreadcrumb(currentPath) { }
        // 文件页面的全局功能
        Box {
            Row(
                modifier = Modifier.width(200.dp).absolutePadding(left = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                var showSearch by remember { mutableStateOf(false) }
                var showAdd by remember { mutableStateOf(false) }
                //搜索按钮
                IconButton(
                    onClick = { showSearch = !showSearch },
                    modifier = Modifier
                        .background(AppColorsProvider.current.card, shape = RoundedCornerShape(50))
                        .size(30.dp)
//                        .border(2.dp, AppColorsProvider.current.topBarColor,shape = RoundedCornerShape(20))
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "搜索",
                        modifier = Modifier.size(20.dp),
                        tint = AppColorsProvider.current.firstIcon
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                //添加按钮
                Box {

                    IconButton(
                        onClick = { showAdd = !showAdd },
                        modifier = Modifier
                            .background(Color(0xFF647DfD), shape = RoundedCornerShape(50))
                            .size(30.dp)

                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "添加",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Unspecified
                        )
                    }
                    //添加按钮菜单操作
                    DropdownMenu(
                        modifier = Modifier.border(
                            width = 10.dp,
                            color = AppColorsProvider.current.card,
                            shape = RoundedCornerShape(10.dp)
                        ).background(AppColorsProvider.current.card),
                        expanded = showAdd,
                        offset = DpOffset(40. dp, (-20).dp),
                        onDismissRequest = { showAdd = false }
                    ) {
                        cdPageAddDropdownMenu.forEach { pageAddDropdownMenu ->
                            PageDropdownMenuItem(pageAddDropdownMenu)
                        }
                    }
                }

            }
        }

    }
}