package model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector
import router.RouterUrls


/**
 * @Description:菜单信息
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/6/2
 */
data class MenuInfo(val iconPath: String, var title: String, var menuUrl: String)
data class PageAddDropdownMenu(val icon: ImageVector, var title:String)

object CDMenuInfos{
    val MENU_INFOS = listOf(
        MenuInfo("icons/menu/menu_file.svg", "文件", RouterUrls.URL_FILE),
        MenuInfo("icons/menu/menu_recent.svg", "最常使用", RouterUrls.URL_RECENT),
        MenuInfo("icons/menu/menu_material.svg", "我的资料", RouterUrls.URL_MATERIAL),
        MenuInfo("icons/menu/menu_image.svg", "相册", RouterUrls.URL_IMAGE),
        MenuInfo("icons/menu/menu_star.svg", "收藏夹", RouterUrls.URL_STAR),
        MenuInfo("icons/menu/menu_lock.svg", "密码箱", RouterUrls.URL_LOCK),
        MenuInfo("icons/menu/menu_collect.svg", "订阅", RouterUrls.URL_COLLECT),
        MenuInfo("icons/menu/menu_recycleBin.svg", "回收站", RouterUrls.URL_RECYCLE_BIN),
        MenuInfo("icons/menu/menu_transmission.svg", "传输列表", RouterUrls.URL_TRANSMISSION),
        MenuInfo("icons/menu/menu_cloud.svg", "备份空间", RouterUrls.URL_CLOUD),
    )

    var PAGE_ADD_DROPDOWN_MENU = listOf(
        PageAddDropdownMenu(Icons.Default.AddCircle, "新建文件夹"),
        PageAddDropdownMenu(Icons.Default.Send, "上传文件"),
        PageAddDropdownMenu(Icons.Default.Share, "上传文件夹"),
        PageAddDropdownMenu(Icons.Default.AddCircle, "新建文档"),
    )
}
