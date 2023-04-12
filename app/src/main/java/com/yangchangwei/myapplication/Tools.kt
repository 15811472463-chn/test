package com.yangchangwei.myapplication

/**
 * @Author : XXX
 * @Date : 2023/4/12 7:56 PM
 * @Description :
 */
object Tools {
    fun millisToStr0(millis: Long): String {
        var totalSeconds = millis / 1000
        return if (totalSeconds < 60) {
            totalSeconds.toString() + "秒"
        } else if (totalSeconds < 3600) {
            val m = totalSeconds / 60
            val s = totalSeconds % 60
            m.toString() + "分" + s + "秒"
        } else {
            val h = totalSeconds / 3600
            totalSeconds = totalSeconds % 3600
            val m = totalSeconds / 60
            val s = totalSeconds % 60
            h.toString() + "小时" + m + "分" + s + "秒"
        }
    }
}