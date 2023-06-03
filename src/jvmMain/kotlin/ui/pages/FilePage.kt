package ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.AppConfig
import model.CDMenuInfos.CD_PAGE_ADD_DROPDOWN_MENU
import model.PageAddDropdownMenu
import ui.pages.file.FileList
import ui.theme.AppColorsProvider


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
 * 列表工具栏
 */
@Composable
fun ListForFile() {
    Column {
        val cdPageAddDropdownMenu = CD_PAGE_ADD_DROPDOWN_MENU
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            //选择按钮
            var showChange by remember { mutableStateOf(false) }
            RadioButton(
                colors = RadioButtonDefaults.colors(
                    selectedColor = AppColorsProvider.current.card,
                    unselectedColor = AppColorsProvider.current.firstText
                ),
                selected = false,
                onClick = {}
            )
            Text(
                text = "共9项", fontSize = 12.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                color = AppColorsProvider.current.firstText
            )

            Spacer(Modifier.width(AppConfig.windowMinWidth -600.dp))

            //按修改时间排序
            Button(
                onClick = { showChange = !showChange },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = AppColorsProvider.current.card,
                    contentColor = AppColorsProvider.current.firstText
                )
            ) {
                Icon(
                    Icons.Default.Share,
                    contentDescription = "按修改时间排序",
                    modifier = Modifier.size(20.dp),
                    tint = AppColorsProvider.current.firstIcon
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = "按修改时间排序", fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    color = AppColorsProvider.current.firstText
                )
            }
            //按修改时间排序操作
            DropdownMenu(
                modifier = Modifier.border(
                    width = 10.dp,
                    color = AppColorsProvider.current.card,
                    shape = RoundedCornerShape(10.dp)
                ).background(AppColorsProvider.current.card),
                expanded = showChange,
                offset = DpOffset((AppConfig.windowMinWidth/2), 0.dp),
                onDismissRequest = { showChange = false }
            ) {
                cdPageAddDropdownMenu.forEach { pageAddDropdownMenu ->
                    PageDropdownMenuItem(pageAddDropdownMenu)
                }
            }

            //视图切换按钮
            Button(
                onClick = {  },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = AppColorsProvider.current.card,
                    contentColor = AppColorsProvider.current.firstText
                )
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "视图切换",
                    tint = AppColorsProvider.current.firstIcon
                )
            }

        }
    }
}

/**
 * 文件页面工具栏
 */
@Composable
fun FilePageTools() {
    val cdPageAddDropdownMenu = CD_PAGE_ADD_DROPDOWN_MENU
    Row(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.width(AppConfig.windowMinWidth - 250.dp - 245.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "文件",
                fontSize = 18.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                color = AppColorsProvider.current.firstText
            )
        }
        Row(
            modifier = Modifier.width(200.dp).absolutePadding(left = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            var showSearch by remember { mutableStateOf(false) }
            var showAdd by remember { mutableStateOf(false) }
            //搜索按钮
            Button(
                onClick = { showSearch = !showSearch },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = AppColorsProvider.current.card,
                    contentColor = AppColorsProvider.current.firstText
                )
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "搜索",
                    modifier = Modifier.size(20.dp),
                    tint = AppColorsProvider.current.firstIcon
                )
            }


            //添加按钮
            Button(
                onClick = { showAdd = !showAdd },
                shape = RoundedCornerShape(50),
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "添加",
                    modifier = Modifier.size(20.dp),
                    tint = AppColorsProvider.current.card
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
                offset = DpOffset((50).dp, 0.dp),
                onDismissRequest = { showAdd = false }
            ) {
                cdPageAddDropdownMenu.forEach { pageAddDropdownMenu ->
                    PageDropdownMenuItem(pageAddDropdownMenu)
                }
            }
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
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(
                pageAddDropdownMenu.icon,
                contentDescription = pageAddDropdownMenu.title,
                modifier = Modifier.size(20.dp),
                tint = AppColorsProvider.current.firstIcon
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = pageAddDropdownMenu.title, color = AppColorsProvider.current.firstText)
        }
    }
}

