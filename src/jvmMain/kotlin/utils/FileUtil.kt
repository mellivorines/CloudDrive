package utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource


/**
 * @Description:文件相关工具类
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/7/24
 */
object FileUtil {
    @Composable
    fun getFileIcon(type: Int): Painter {
        return when (type) {
            0 -> painterResource("icons/file/type/folder.svg")
            1 -> painterResource("icons/file/type/music.svg")
            2 -> painterResource("icons/file/type/video.svg")
            3 -> painterResource("icons/file/type/photo.svg")
            4 -> painterResource("icons/file/type/txt.svg")
            5 -> painterResource("icons/file/type/pdf.svg")
            6 -> painterResource("icons/file/type/word.svg")
            7 -> painterResource("icons/file/type/ppt.svg")
            8 -> painterResource("icons/file/type/excel.svg")
            9 -> painterResource("icons/file/type/lianjie.svg")
            10 -> painterResource("icons/file/type/zip.svg")
            else -> painterResource("icons/file/type/unknown.svg")
        }
    }
}

