package base

import androidx.compose.ui.unit.dp
import java.io.File


/**
 * @Description:应用初始化配置
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/5/31
 */
object AppConfig {

    val topBarHeight = 50.dp
    val menuBarMaxWidth = 200.dp
    val menuBarMinWidth = 60.dp
    val windowMinWidth = 1000.dp
    val windowMinHeight = 640.dp
    val basePadding = 10.dp

    const val roundedPercent = 15

    // 应用缓存目录
    val cacheRootDir = System.getProperty("user.home") + File.separator + "Library" + File.separator + "CloudDrive"
}