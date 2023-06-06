package ui.pages.file

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.AppConfig
import model.CDMenuInfos
import ui.pages.PageDropdownMenuItem
import ui.theme.AppColorsProvider


/**
 * @Description:文件页面工具栏
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/6/4
 */
@Composable
fun FilePageTools() {
    val cdPageAddDropdownMenu = CDMenuInfos.CD_PAGE_ADD_DROPDOWN_MENU
    Row(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box{
            Text(
                text = "文件",
                fontSize = 18.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                color = AppColorsProvider.current.firstText
            )
        }
        Box{
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
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "搜索",
                        modifier = Modifier.size(20.dp),
                        tint = AppColorsProvider.current.firstIcon
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                //添加按钮
                IconButton(
                    onClick = { showAdd = !showAdd },
                    modifier = Modifier
                        .background(color = Color.Blue, shape = RoundedCornerShape(50))
                        .size(30.dp)
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
}