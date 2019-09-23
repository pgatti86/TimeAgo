package com.ilbose.time_ago

import android.content.Context
import com.ilbose.R
import java.util.*

/**
 * Created by riccardo on 2019-09-23.
 */

const val SECOND = 1000L
const val MINUTE = SECOND * 60L
const val HOUR = MINUTE * 60L
const val DAY = HOUR * 24L
const val WEEK = DAY * 7L
const val YEAR = DAY * 365L

fun Date.timeAgo(context: Context): String = timeAgo(context, this.time)

fun Calendar.timeAgo(context: Context): String = timeAgo(context, this.timeInMillis)

fun timeAgo(context: Context, millis: Long): String {
    val elapsedTime = Date().time - millis

    return when {
        elapsedTime in 0 until (SECOND * 5) -> context.getString(R.string.just_now)

        elapsedTime < MINUTE -> context.getString(
            R.string.seconds_ago,
            elapsedTime / SECOND
        )

        elapsedTime < MINUTE * 2 -> context.getString(R.string.a_minute_ago)

        elapsedTime < HOUR -> context.getString(
            R.string.minutes_ago,
            elapsedTime / MINUTE
        )

        elapsedTime < HOUR * 2 -> context.getString(R.string.an_hour_ago)

        elapsedTime < DAY -> context.getString(
            R.string.hours_ago,
            elapsedTime / HOUR
        )

        elapsedTime < DAY * 2 -> context.getString(R.string.yesterday)

        elapsedTime < WEEK -> context.getString(
            R.string.days_ago,
            elapsedTime / DAY
        )

        elapsedTime < WEEK * 2 -> context.getString(R.string.last_week)

        elapsedTime < YEAR -> context.getString(
            R.string.weeks_ago,
            elapsedTime / WEEK
        )

        elapsedTime < YEAR * 2 -> context.getString(R.string.last_year)

        elapsedTime >= YEAR -> context.getString(
            R.string.years_ago,
            elapsedTime / YEAR
        )

        else -> throw IllegalStateException("The date requested was in future")
    }
}