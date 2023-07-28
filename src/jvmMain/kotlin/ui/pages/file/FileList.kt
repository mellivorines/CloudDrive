package ui.pages.file

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.CDMenuInfos
import model.CDMenuInfos.FILE_INFO
import model.FileInfo
import theme.AppColorsProvider
import ui.components.CustomizeDropdownMenu
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
    ListScreen(fileInfos = FILE_INFO)
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
fun ListScreen(fileInfos: List<FileInfo>) {
    val sortDescend by remember { mutableStateOf(false) }
    var sortColumn by remember { mutableStateOf("name") }

    val sortedList = remember(fileInfos, sortColumn) {
        if (sortDescend) {
            when (sortColumn) {
                "name" -> fileInfos.sortedByDescending { it.name }
                "createdTime" -> fileInfos.sortedByDescending { it.createdTime }
                "size" -> fileInfos.sortedByDescending { it.size }
                else -> fileInfos
            }
        } else {
            when (sortColumn) {
                "name" -> fileInfos.sortedBy { it.name }
                "createdTime" -> fileInfos.sortedBy { it.createdTime }
                "size" -> fileInfos.sortedBy { it.size }
                else -> fileInfos
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
fun ListItemView(item: FileInfo, onClick: (title: Any) -> Unit) {
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
                    //更多操作菜单
                    //自定义
                    CustomizeDropdownMenu(
                        cdPageAddDropdownMenu,
                        showChange
                    ) {
                        showChange = false
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