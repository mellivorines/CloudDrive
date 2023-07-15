package ui.pages.file

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.AppColorsProvider


/**
 * 文件夹面包屑
 * @param [currentPath]文件夹路径数据
 * @param [onClick]点击事件的处理逻辑
 */
@Composable
fun FolderBreadcrumb(currentPath: List<String>, onClick: () -> Unit) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // 显示文件夹名称列表
            for (index in currentPath.indices) {
                val folderName = currentPath[index]
                TextButton(

                    modifier = Modifier
                        .height(40.dp),

                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    ),

                    shape = AbsoluteRoundedCornerShape(10.dp, 30.dp, 5.dp, 10.dp),
                    onClick = { }
//                    {
                    //删除当前点击对象的所有最后面元素
//                        currentPath.removeRange(index + 1, currentPath.size)
//                    }
                ) {

                    Text(
                        text = folderName,
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        color = AppColorsProvider.current.firstText
                    )
                }
                // 添加分隔符
                if (index < currentPath.size - 1) {
                    Text(text = ">")
                }
            }
        }
    }

}