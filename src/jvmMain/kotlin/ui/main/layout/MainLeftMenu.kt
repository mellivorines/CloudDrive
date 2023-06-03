package ui.main.layout

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import base.AppConfig
import model.CDButtonInfo
import model.CDMenuInfo
import model.CDMenuInfos.CD_BUTTON_INFOS
import model.CDMenuInfos.CD_MENU_INFOS_1
import model.CDMenuInfos.CD_MENU_INFOS_2
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import router.CDNavigatorManager
import router.CDNavigatorManager.navigator
import ui.customcomponents.progressbar
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
    val cdMenuInfos = CD_MENU_INFOS_1
    val cdMenuInfos1 = CD_MENU_INFOS_2
    val cdButtonInfos = CD_BUTTON_INFOS

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //第一组菜单
        Items(cdMenuInfos)
        //分割线
        Divider(
            Modifier.height(15.dp).padding(top = 12.dp, end = 10.dp),
            color = AppColorsProvider.current.card,
            thickness = 1.dp,
            startIndent = 10.dp
        )
        //第二组菜单
        Items(cdMenuInfos1)
        //按钮
        buttonForPhoneOrIPad(cdButtonInfos)
        //空间进度条
        progressbar(value = 0.3f)
    }


}

/**
 * 自定义按钮
 */
@Composable
fun buttonForPhoneOrIPad(cdButtonInfos: List<CDButtonInfo>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.height(42.dp)
    )
    {
        for (buttonInfo in cdButtonInfos) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = AppColorsProvider.current.background,
                    contentColor = AppColorsProvider.current.firstText
                ),
                border = BorderStroke(width = 1.dp, color = AppColorsProvider.current.card),
                onClick = { }
            ) {
                Icon(
                    painterResource(buttonInfo.iconPath),
                    contentDescription = buttonInfo.title,
                    modifier = Modifier.size(20.dp),
                    tint = AppColorsProvider.current.firstIcon,
                )
                Text(buttonInfo.title)
            }
            if (buttonInfo.title != "iPad端") {
                Spacer(
                    modifier = Modifier.width(10.dp).height(10.dp)
                        .background(AppColorsProvider.current.background)
                )
            }
        }
    }
}

@Composable
fun Items(list: List<CDMenuInfo>) {
    Column(
        modifier = Modifier.padding(top = 10.dp),
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
        modifier = Modifier.fillMaxWidth().height(42.dp).absolutePadding(left = 15.dp, right = 15.dp).onClick {
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
                .clip(RoundedCornerShape(15))//设置盒子圆角
                .background(color = color)//设置背景颜色
                .onPointerEvent(PointerEventType.Enter) {//鼠标移入改变背景颜色
                    active = true
                    color = card
                }
                .onPointerEvent(PointerEventType.Exit) {//鼠标移出改变背景颜色
                    active = false
                    color = background
                }
        ) {
            Icon(
                painterResource(iconPath),
                contentDescription = title,
                modifier = Modifier.let {
                    if (title == "最常使用" || title == "我的资料") {
                        it.absolutePadding(left = 60.dp, right = 20.dp, top = 10.dp).size(20.dp)
                    } else {
                        it.absolutePadding(left = 20.dp, right = 20.dp, top = 10.dp).size(20.dp)
                    }
                },
                tint = AppColorsProvider.current.firstIcon
            )
            Text(
                text = title,
                modifier = Modifier.let {
                    if (title == "最常使用" || title == "我的资料") {
                        it.absolutePadding(left = 100.dp, right = 20.dp, top = 10.dp)
                    } else {
                        it.absolutePadding(left = 60.dp, right = 20.dp, top = 10.dp)
                    }
                },
                fontSize = 15.sp,
                maxLines = 1,
                color = AppColorsProvider.current.firstText,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}
