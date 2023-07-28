package ui.layout

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.AppConfig
import model.CDMenuInfos.MENU_INFOS
import model.MenuInfo
import moe.tlaster.precompose.navigation.NavOptions
import router.CDNavigatorManager.navigator
import theme.AppColorsProvider


/**
 * @Description:主页左边菜单栏组件
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/6/1
 */
@Composable
fun LeftMenu() {
    var width by remember { mutableStateOf(AppConfig.menuBarMaxWidth.value.toInt()) }

    Column(
        modifier = Modifier
            .width(width.dp)
            .fillMaxHeight()
            .background(AppColorsProvider.current.background)
    ) {
        Box(
            modifier = Modifier
                .height(AppConfig.topBarHeight * 2)
                .width(width.dp)
                .padding(top = AppConfig.topBarHeight, start = 20.dp, end = 20.dp)
        ) {
            IconButton(
                onClick = {
                    width = if (width.dp == AppConfig.menuBarMaxWidth) {
                        AppConfig.menuBarMinWidth.value.toInt()
                    } else {
                        AppConfig.menuBarMaxWidth.value.toInt()
                    }
                },
                modifier = Modifier
                    .height(AppConfig.topBarHeight * 2)
                    .width(width.dp),
                enabled = true,
            ) {
                Icon(
                    painterResource("icons/app/app.svg"),
                    contentDescription = "title",
                    tint = Color.Unspecified
                )
            }
        }
        CDMenu(width)
    }

}

/**
 * 菜单项
 */
@Composable
private fun CDMenu(width: Int) {
    val cdMenuInfos = MENU_INFOS

    Column(
        modifier = Modifier
            .width(width.dp)
            .animateContentSize(spring(0.55f, Spring.StiffnessMediumLow))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Items(cdMenuInfos, width)
    }


}

@Composable
fun Items(list: List<MenuInfo>, width: Int) {
    Column(
        modifier = Modifier.padding(top = AppConfig.basePadding).width(width.dp - AppConfig.basePadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        list.forEach { menu ->
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
@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
private fun CDMenuItem(
    iconPath: String,
    title: String,
    onClick: (title: Any) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(50.dp).onClick {
            onClick(title)
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val background = AppColorsProvider.current.background
        val card = AppColorsProvider.current.card
        var color by remember { mutableStateOf(background) }
        var active by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
                .fillMaxSize()
                .clip(RoundedCornerShape(AppConfig.roundedPercent))
                .background(color = color)
                .onPointerEvent(PointerEventType.Enter) {
                    active = true
                    color = card
                }
                .onPointerEvent(PointerEventType.Exit) {
                    active = false
                    color = background
                }
        ) {
            Row {
                Box(modifier = Modifier.height(AppConfig.topBarHeight), contentAlignment = Alignment.Center) {
                    Icon(
                        painterResource(iconPath),
                        contentDescription = title,
                        modifier = Modifier.padding(AppConfig.basePadding).size(AppConfig.topBarHeight / 2),
                        tint = Color.Unspecified
                    )
                }
                Box(modifier = Modifier.height(AppConfig.topBarHeight), contentAlignment = Alignment.Center) {
                    Text(
                        text = title,
                        fontSize = 15.sp,
                        maxLines = 1,
                        color = AppColorsProvider.current.firstText,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

    }
}
