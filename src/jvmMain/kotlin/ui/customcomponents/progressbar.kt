package ui.customcomponents

import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.AppColorsProvider


/**
 * @Description:自定义进度条
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/6/2
 */
@Composable
fun progressbar(modifier: Modifier = Modifier, value: Float) {
    Column(
        modifier = modifier.height(60.dp).absolutePadding(left = 10.dp, top = 3.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(modifier = Modifier.absolutePadding(left = 14.dp)) {
            Text(
                text = "528G/12.47T",
                color = AppColorsProvider.current.firstText,
                modifier = modifier.padding(end = 16.dp),
                fontSize = 12.sp
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = "$value%",
                color = AppColorsProvider.current.firstText,
                modifier = modifier.padding(end = 16.dp)
            )
        }
        LinearProgressIndicator(
            modifier=Modifier.height(6.dp).absolutePadding(left = 10.dp, right = 16.dp),
            progress = value,
            color = Color.Blue,
            strokeCap = StrokeCap.Round,
            backgroundColor = Color.Gray
        )
    }
}