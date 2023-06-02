package ui.main.layout

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.AppConfig
import model.CDMenuInfo
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import router.CDNavigatorManager
import router.RouterUrls
import ui.theme.AppColorsProvider


/**
 * @Description:主页左边菜单栏组件
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/6/1
 */
@Composable
fun MainLeftMenu() {
    val navigator = CDNavigatorManager.navigator

    Column(modifier = Modifier.width(245.dp).fillMaxHeight().background(AppColorsProvider.current.background)) {
        Spacer(
            modifier = Modifier.fillMaxWidth().height(AppConfig.topBarHeight)
                .background(AppColorsProvider.current.background)
        )
        CDMenu(navigator)
    }

}

/**
 * 菜单项
 */
@Composable
private fun CDMenu(navigator: Navigator) {
    val mutableListOf = listOf(
        CDMenuInfo("icons/menu/menu_file.svg", "文件", RouterUrls.URL_FILE),
        CDMenuInfo("icons/menu/menu_image.svg", "相册", RouterUrls.URL_IMAGE),
        CDMenuInfo("icons/menu/menu_lock.svg", "密码箱", RouterUrls.URL_LOCK),
        CDMenuInfo("icons/menu/menu_recycleBin.svg", "回收站", RouterUrls.URL_RECYCLE_BIN),
    )
    val list = remember { mutableStateListOf<String>() }
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        mutableListOf.forEach { menu ->
            CDMenuItem(menu.iconPath, menu.title) {
                while (navigator.canGoBack) {
                    navigator.popBackStack()
                }
                navigator.navigate(menu.menuUrl, NavOptions(launchSingleTop = true))
            }

        }
    }
}

/**
 * 菜单项框
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CDMenuItem(
    iconPath: String,
    title: String,
    selectedMenuTag: String? = null,
    markLogoPath: String? = null,
    type: Int = 0,
    onClick: (title: Any) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(50.dp).padding(horizontal = 15.dp).onClick {
            onClick(title)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(iconPath),
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
