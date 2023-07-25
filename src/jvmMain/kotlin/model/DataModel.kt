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
data class MenuInfo(
    val iconPath: String,
    var title: String,
    var menuUrl: String
)

data class PageAddDropdownMenu(
    val icon: ImageVector,
    var title: String
)

data class FileInfo(
    val name: String,
    val createdTime: String,
    val size: String,
    val type: Int
)

object CDMenuInfos {
    val MENU_INFOS = listOf(
        MenuInfo("icons/menu/fileText.svg", "文件", RouterUrls.URL_FILE),
        MenuInfo("icons/menu/recent.svg", "最常使用", RouterUrls.URL_RECENT),
        MenuInfo("icons/menu/folderHeart.svg", "我的资料", RouterUrls.URL_MATERIAL),
        MenuInfo("icons/menu/picture.svg", "相册", RouterUrls.URL_IMAGE),
        MenuInfo("icons/menu/folderStar.svg", "收藏夹", RouterUrls.URL_STAR),
        MenuInfo("icons/menu/lock.svg", "密码箱", RouterUrls.URL_LOCK),
        MenuInfo("icons/menu/collect.svg", "订阅", RouterUrls.URL_COLLECT),
        MenuInfo("icons/menu/trash.svg", "回收站", RouterUrls.URL_RECYCLE_BIN),
        MenuInfo("icons/menu/transmission.svg", "传输列表", RouterUrls.URL_TRANSMISSION),
        MenuInfo("icons/menu/cloud.svg", "备份空间", RouterUrls.URL_CLOUD),
    )

    var PAGE_ADD_DROPDOWN_MENU = listOf(
        PageAddDropdownMenu(Icons.Default.AddCircle, "新建文件夹"),
        PageAddDropdownMenu(Icons.Default.Send, "上传文件"),
        PageAddDropdownMenu(Icons.Default.Share, "上传文件夹"),
        PageAddDropdownMenu(Icons.Default.AddCircle, "新建文档"),
    )

    val FILE_INFO = listOf(
        FileInfo("File 1", "2022-05-30 12:35:17", "20 MB", 0),
        FileInfo("File 2", "2022-05-29 09:42:05", "15 MB", 1),
        FileInfo("File 3", "2022-05-28 16:50:22", "10 MB", 2),
        FileInfo("File 4", "2022-05-28 16:50:22", "10 MB", 3),
        FileInfo("File 5", "2022-05-28 16:50:22", "10 MB", 4),
        FileInfo("File 6", "2022-05-28 16:50:22", "10 MB", 5),
        FileInfo("File 7", "2022-05-28 16:50:22", "10 MB", 6),
        FileInfo("File 8", "2022-05-28 16:50:22", "10 MB", 7),
        FileInfo("File 9", "2022-05-28 16:50:22", "10 MB", 8),
        FileInfo("File 10", "2022-05-28 16:50:22", "10 MB", 9),
        FileInfo("File 11", "2022-05-28 16:50:22", "10 MB", 10),
        FileInfo("File 12", "2022-05-28 16:50:22", "10 MB", 11),
    )
}
