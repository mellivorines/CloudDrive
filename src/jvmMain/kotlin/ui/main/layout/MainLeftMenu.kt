package ui.main.layout

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.AppConfig
import moe.tlaster.precompose.navigation.NavOptions
import router.CDNavigatorManager
import router.CDNavigatorManager.navigator
import router.RouterUrls
import ui.common.theme.AppColorsProvider


/**
 * @Description:主页左边菜单栏组件
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/6/1
 */
@Composable
fun MainLeftMenu() {
    var navigator = CDNavigatorManager.navigator

    Column(modifier = Modifier.width(245.dp).fillMaxHeight().background(AppColorsProvider.current.background)) {
        Spacer(
            modifier = Modifier.fillMaxWidth().height(AppConfig.topBarHeight)
                .background(AppColorsProvider.current.topBarColor)
        )
        MenuOne()
        MenuLine()
//        MenuOne()

    }

}

@Composable
private fun MenuLine(){
    Canvas(modifier = Modifier.size(2.dp)) {
        drawLine(
            color = Color.Gray,
            start = Offset(40f, 100f),
            end = Offset(440f, 100f),
            strokeWidth = 2f,
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun MenuOne(){
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MenuItem("icons/menu/menu_file.svg", "文件") {
            while (navigator.canGoBack) {
                navigator.popBackStack()
            }
            navigator.navigate(RouterUrls.URL_FILE, NavOptions(launchSingleTop = true))
        }
        MenuItem("icons/menu/menu_image.svg", "相册") {
            navigator.navigate(RouterUrls.URL_IMAGE, NavOptions(launchSingleTop = true))
        }
        MenuItem("icons/menu/menu_lock.svg", "密码箱") {
            navigator.navigate(RouterUrls.URL_LOCK, NavOptions(launchSingleTop = true))
        }
        MenuItem("icons/menu/menu_recycleBin.svg", "回收站") {
            navigator.navigate(RouterUrls.URL_RECYCLE_BIN, NavOptions(launchSingleTop = true))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MenuItem(
    logoPath: String,
    title: String,
    markLogoPath: String? = null,
    type: Int = 0,
    onClick: (title: Any) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(50.dp).padding(horizontal = 15.dp).onClick { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(logoPath),
            contentDescription = title,
            modifier = Modifier.size(20.dp),
            tint = AppColorsProvider.current.firstIcon
        )
        Text(
            text = title,
            modifier = Modifier.weight(1f).padding(horizontal = 20.dp),
            fontSize = 15.sp,
            maxLines = 1,
            color = AppColorsProvider.current.firstText,
            overflow = TextOverflow.Ellipsis
        )
    }
}