package ui.pages.file

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.AppConfig
import model.CDMenuInfos
import ui.pages.PageDropdownMenuItem
import ui.theme.AppColorsProvider
import utils.FileUtil.getFileIcon


/**
 * @Description:
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/6/3
 */
@Composable
fun FileList() {
    ListScreen(listItems = listItems)
}

@Composable
fun ListHeader(sortColumn: (String) -> Unit, sortDescend: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.width(200.dp)
        ) {
            SortableColumn("名称", sortColumn, "name", sortDescend)
        }
        Box {
            SortableColumn("创建时间", sortColumn, "createdTime", sortDescend)
        }
        Box {
            SortableColumn("大小", sortColumn, "size", sortDescend)
        }
    }
}

/**
 * 排序列
 */
@Composable
fun SortableColumn(title: String, sortColumn: (String) -> Unit, columnName: String, sortDescend: Boolean) {
    var sortDescending by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .clickable(onClick = {
                if (sortDescend) {
                    sortDescending = !sortDescending
                }
                sortColumn(columnName)
            }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier,
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            fontStyle = FontStyle.Normal,
            color = AppColorsProvider.current.firstText
        )
        Icon(
            if (sortDescending) Icons.Default.KeyboardArrowUp else Icons.Default.ArrowDropDown,
            contentDescription = "",
            tint = Color.Gray
        )
    }
}

/**
 * 列图
 */
@Composable
fun ListScreen(listItems: List<ListItem>) {
    val sortDescend by remember { mutableStateOf(false) }
    var sortColumn by remember { mutableStateOf("name") }

    val sortedList = remember(listItems, sortColumn) {
        if (sortDescend) {
            when (sortColumn) {
                "name" -> listItems.sortedByDescending { it.name }
                "createdTime" -> listItems.sortedByDescending { it.createdTime }
                "size" -> listItems.sortedByDescending { it.size }
                else -> listItems
            }
        } else {
            when (sortColumn) {
                "name" -> listItems.sortedBy { it.name }
                "createdTime" -> listItems.sortedBy { it.createdTime }
                "size" -> listItems.sortedBy { it.size }
                else -> listItems
            }
        }

    }

    val searchText by remember { mutableStateOf("") }
    val filteredList = sortedList.filter { it.name.contains(searchText, ignoreCase = true) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ListHeader(sortColumn = { sortColumn = it }, sortDescend)
        }
        Column(
            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            filteredList.forEach { item ->
                ListItemView(item = item) {
                }
            }
        }
    }

}

/**
 * 列表项视图
 */
@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ListItemView(item: ListItem, onClick: (title: Any) -> Unit) {
    val cdPageAddDropdownMenu = CDMenuInfos.PAGE_ADD_DROPDOWN_MENU
    val background = AppColorsProvider.current.card
    val card = AppColorsProvider.current.background
    var color by remember { mutableStateOf(background) }
    var active by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(2.dp)
            .onClick { onClick(item.name) }
            .clip(RoundedCornerShape(15))//设置盒子圆角
            .background(color = color)//设置背景颜色
            .onPointerEvent(PointerEventType.Enter) {//鼠标移入改变背景颜色
                active = true
                color = card
            }
            .onPointerEvent(PointerEventType.Exit) {//鼠标移出改变背景颜色
                active = false
                color = background
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier.width(200.dp)
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                var showChange by remember { mutableStateOf(false) }
                Box(
                    modifier = Modifier.width(50.dp)
                ) {
                    Icon(
                        getFileIcon(item.type),
                        contentDescription = "文件类型图标",
                        tint = Color.Unspecified
                    )
                }
                Box(
                    modifier = Modifier.width(100.dp)
                ) {
                    Text(
                        text = item.name,
                        color = AppColorsProvider.current.firstText
                    )
                }

                //更多按钮设置（moreOperations）
                Box(
                    modifier = Modifier.width(50.dp)
                ) {
                    IconButton(
                        { showChange = !showChange },
                    ) {
                        Icon(
                            if (active) Icons.Default.MoreVert else Icons.Default.MoreVert,
                            contentDescription = "更多操作",
                            tint = AppColorsProvider.current.firstIcon
                        )
                    }
                }

                //更多操作菜单
                DropdownMenu(
                    modifier = Modifier.border(
                        width = 10.dp,
                        color = AppColorsProvider.current.card,
                        shape = RoundedCornerShape(10.dp)
                    ).background(AppColorsProvider.current.card),
                    expanded = showChange,
                    offset = DpOffset((AppConfig.windowMinWidth / 2), 0.dp),
                    onDismissRequest = { showChange = false }
                ) {
                    //更多操作菜单项
                    cdPageAddDropdownMenu.forEach { pageAddDropdownMenu ->
                        PageDropdownMenuItem(pageAddDropdownMenu)
                    }
                }

            }
        }

        Box {
            Text(
                text = item.createdTime,
                color = AppColorsProvider.current.firstText
            )

        }
        Box {

            Text(
                text = item.size,
                color = AppColorsProvider.current.firstText
            )
        }
    }

}

val listItems = listOf(
    ListItem("File 1", "2022-05-30 12:35:17", "20 MB", 0),
    ListItem("File 2", "2022-05-29 09:42:05", "15 MB", 1),
    ListItem("File 3", "2022-05-28 16:50:22", "10 MB", 2),
    ListItem("File 4", "2022-05-28 16:50:22", "10 MB", 3),
    ListItem("File 5", "2022-05-28 16:50:22", "10 MB", 4),
    ListItem("File 6", "2022-05-28 16:50:22", "10 MB", 5),
    ListItem("File 7", "2022-05-28 16:50:22", "10 MB", 6),
    ListItem("File 8", "2022-05-28 16:50:22", "10 MB", 7),
    ListItem("File 9", "2022-05-28 16:50:22", "10 MB", 8),
    ListItem("File 10", "2022-05-28 16:50:22", "10 MB", 9),
    ListItem("File 11", "2022-05-28 16:50:22", "10 MB", 10),
    ListItem("File 12", "2022-05-28 16:50:22", "10 MB", 11),
)


data class ListItem(
    val name: String,
    val createdTime: String,
    val size: String,
    val type: Int
)