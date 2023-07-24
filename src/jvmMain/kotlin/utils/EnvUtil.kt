package utils

import java.util.*

object EnvUtil {
    private val osName: String = System.getProperty("os.name", "generic")

    fun isMac() = osName.lowercase(Locale.getDefault()).contains("mac")

    fun isWindows() = osName.contains("windows")

    fun isLinux() = osName.contains("nix") || osName.contains("nux") || osName.contains("aix")
}