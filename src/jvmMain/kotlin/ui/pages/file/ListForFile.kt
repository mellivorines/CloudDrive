package ui.pages.file

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.CDMenuInfos
import ui.pages.PageDropdownMenuItem
import theme.AppColorsProvider


/**
 * @Description:列表工具栏
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/6/4
 */
@Composable
fun ListForFile() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val cdPageAddDropdownMenu = CDMenuInfos.PAGE_ADD_DROPDOWN_MENU
        Box {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
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
            }

        }
        Box {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //选择按钮
                var showChange by remember { mutableStateOf(false) }
                //按修改时间排序
                TextButton(
                    onClick = { showChange = !showChange },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = AppColorsProvider.current.card,
                        contentColor = AppColorsProvider.current.firstText
                    ),
                    border = BorderStroke(2.dp, AppColorsProvider.current.topBarColor)
                ) {
                    Icon(
                        Icons.Default.Share,
                        contentDescription = "按修改时间排序",
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
                    onDismissRequest = { showChange = false }
                ) {
                    cdPageAddDropdownMenu.forEach { pageAddDropdownMenu ->
                        PageDropdownMenuItem(pageAddDropdownMenu)
                    }
                }

                //视图切换按钮
                TextButton(
                    onClick = { },
                    modifier = Modifier
                        .background(color = AppColorsProvider.current.card, shape = RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = AppColorsProvider.current.card,
                        contentColor = AppColorsProvider.current.firstText
                    ),
                    border = BorderStroke(2.dp, AppColorsProvider.current.topBarColor)
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
}