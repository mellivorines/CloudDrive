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
    val windowMinWidth = 1000.dp
    val windowMinHeight = 640.dp
    var fullscreen = false

    // 应用缓存目录
    val cacheRootDir = System.getProperty("user.home") + File.separator + "Library" + File.separator + "CloudDrive"

}