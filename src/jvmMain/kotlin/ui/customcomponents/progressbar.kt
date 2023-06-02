package ui.customcomponents

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.TextUnit
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
        modifier = modifier.absolutePadding(left = 10.dp,top = 3.dp)
    ) {
        Row(modifier = Modifier.absolutePadding(left = 14.dp)) {
            Text(text = "528G/12.47T",
                color = AppColorsProvider.current.firstText,
                modifier = modifier.padding(end = 16.dp),
                fontSize = 12.sp
            )
            Spacer(Modifier.weight(1f))
//            Text(text = "$value%", color = AppColorsProvider.current.firstText, modifier = modifier.padding(end = 16.dp))
        }
        Canvas(modifier.fillMaxWidth().padding(16.dp)) {

            val canvasWidth = size.width  // 画布的宽
            val canvasHeight = size.height  // 画布的高
            val strokeWidth = canvasHeight / 20
            val path = Path()
            path.lineTo(canvasWidth * (value / 100), 0f)

            drawPath(
                path = path,
                style = Stroke(
                    width = 15f,
                    cap = StrokeCap.Round
                ),
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Blue,
                        Color.Cyan
                    )
                )

            )
        }


    }
}