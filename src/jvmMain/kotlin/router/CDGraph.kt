package router

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import ui.pages.*


/**
 * @Description:
 *
 * @author lilinxi
 * @version 1.0.0
 * @since 2023/6/1
 */

object CDNavigatorManager {
    lateinit var navigator: Navigator
}

@Composable
fun CDGraph(){
    val navigator = CDNavigatorManager.navigator
    NavHost(
        navigator = navigator,
        navTransition = remember {
            NavTransition(
                createTransition = fadeIn(),
                destroyTransition = fadeOut(),
                pauseTransition = fadeOut(),
                resumeTransition = fadeIn(),
            )
        }, initialRoute = RouterUrls.URL_FILE
    ) {
        scene(RouterUrls.URL_FILE) {
            FilePage()
        }
        scene(RouterUrls.URL_RECENT) {
            RecentPage()
        }
        scene(RouterUrls.URL_MATERIAL) {
            MaterialPage()
        }
        scene(RouterUrls.URL_IMAGE) {
            ImagePage()
        }
        scene(RouterUrls.URL_STAR) {
            ImagePage()
        }
        scene(RouterUrls.URL_LOCK) {
            LockPage()
        }
        scene(RouterUrls.URL_COLLECT) {
            ImagePage()
        }
        scene(RouterUrls.URL_RECYCLE_BIN) {
            RecycleBinPage()
        }
        scene(RouterUrls.URL_TRANSMISSION) {
            RecycleBinPage()
        }
        scene(RouterUrls.URL_CLOUD) {
            RecycleBinPage()
        }
    }
}