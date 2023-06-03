package ui.pages.file

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.AppConfig
import ui.theme.AppColorsProvider


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
fun ListHeader(sortColumn: (String) -> Unit) {
    Row(modifier = Modifier.padding(16.dp)) {
        SortableColumn("名称", sortColumn, "name")
        SortableColumn("创建时间", sortColumn, "createdTime")
        SortableColumn("大小", sortColumn, "size")
    }
}

@Composable
fun SortableColumn(title: String, sortColumn: (String) -> Unit, columnName: String) {
    Row(
        modifier = Modifier.width(AppConfig.windowMinWidth/4).clickable(onClick = { sortColumn(columnName) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            fontStyle = FontStyle.Normal,
            color = AppColorsProvider.current.firstText
        )
        Icon(
            if (columnName == "name") Icons.Default.ArrowDropDown else Icons.Default.ArrowDropDown,
            contentDescription = "",
            tint = Color.Gray
        )
    }
}


@Composable
fun ListScreen(listItems: List<ListItem>) {
    var sortColumn by remember { mutableStateOf("name") }

    val sortedList = remember(listItems, sortColumn) {
        when (sortColumn) {
            "name" -> listItems.sortedBy { it.name }
            "createdTime" -> listItems.sortedBy { it.createdTime }
            "size" -> listItems.sortedBy { it.size }
            else -> listItems
        }
    }

    val searchText by remember { mutableStateOf("") }
    val filteredList = sortedList.filter { it.name.contains(searchText, ignoreCase = true) }

    Column {
        ListHeader(sortColumn = { sortColumn = it })

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            filteredList.forEach { item ->
                ListItemView(item = item)
            }
        }
    }
}

@Composable
fun ListItemView(item: ListItem) {
    Row(
        modifier = Modifier.height(50.dp).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.width(AppConfig.windowMinWidth/4)
        ) {
            Icon(
                if (item.type != 1)painterResource("icons/content/folder.svg") else painterResource("icons/menu/menu_file.svg"),
                contentDescription = "文件类型图标",
                modifier = Modifier.width((AppConfig.windowMinWidth/4) * 1/4),
                tint = AppColorsProvider.current.firstIcon
            )
            Text(text = item.name,modifier = Modifier.width((AppConfig.windowMinWidth/4) * 3/4), color = AppColorsProvider.current.firstText)
        }
        Text(text = item.createdTime, modifier = Modifier.width(AppConfig.windowMinWidth/4), color = AppColorsProvider.current.firstText)
        Text(text = item.size, modifier = Modifier.width(AppConfig.windowMinWidth/4), color = AppColorsProvider.current.firstText)
    }
}

val listItems = listOf(
    ListItem("File 1", "2022-05-30 12:35:17", "20 MB",1),
    ListItem("File 2", "2022-05-29 09:42:05", "15 MB",1),
    ListItem("File 3", "2022-05-28 16:50:22", "10 MB",2),
    ListItem("File 4", "2022-05-28 16:50:22", "10 MB",2),
    ListItem("File 5", "2022-05-28 16:50:22", "10 MB",2),
    ListItem("File 6", "2022-05-28 16:50:22", "10 MB",2),
    ListItem("File 7", "2022-05-28 16:50:22", "10 MB",2),
    ListItem("File 8", "2022-05-28 16:50:22", "10 MB",2),
    ListItem("File 9", "2022-05-28 16:50:22", "10 MB",2),
    ListItem("File 10", "2022-05-28 16:50:22", "10 MB",2),
    ListItem("File 11", "2022-05-28 16:50:22", "10 MB",2),
    ListItem("File 12", "2022-05-28 16:50:22", "10 MB",2),
)


data class ListItem(
    val name: String,
    val createdTime: String,
    val size: String,
    val type:Int
)