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

const val WEEKS_IN_A_YEAR = 52
const val DAYS_IN_A_YEAR = 365
const val DAYS_IN_A_WEEK = 7
const val HOURS_IN_A_DAY = 24
const val MINUTES_IN_AN_HOUR = 60
const val SECONDS_IN_A_MINUTE = 60

fun Date.timeAgo(context: Context): String = timeAgo(context, time)

fun Calendar.timeAgo(context: Context): String = timeAgo(context, timeInMillis)

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

        else -> throw IllegalStateException("The requested date is in future")
    }
}

fun Date.simpleTimeAgo(context: Context) = simpleTimeAgo(context, time)

fun Calendar.simpleTimeAgo(context: Context) = simpleTimeAgo(context, timeInMillis)

fun simpleTimeAgo(context: Context, millis: Long): String {
    val elapsedTime = Date().time - millis

    return when {

        elapsedTime in 0 until MINUTE -> context.getString(
            R.string.simple_seconds_ago,
            elapsedTime / SECOND
        )

        elapsedTime < HOUR -> context.getString(
            R.string.simple_minutes_ago,
            elapsedTime / MINUTE
        )

        elapsedTime < DAY -> context.getString(
            R.string.simple_hours_ago,
            elapsedTime / HOUR
        )

        elapsedTime < WEEK -> context.getString(
            R.string.simple_days_ago,
            elapsedTime / DAY
        )

        elapsedTime < YEAR -> context.getString(
            R.string.simple_weeks_ago,
            elapsedTime / WEEK
        )

        elapsedTime >= YEAR -> context.getString(
            R.string.simple_years_ago,
            elapsedTime / YEAR
        )

        else -> throw IllegalStateException("The requested date is in future")
    }
}

fun Date.fullTimeAgo(context: Context) = fullTimeAgo(context, time)

fun Calendar.fullTimeAgo(context: Context) = fullTimeAgo(context, timeInMillis)

fun fullTimeAgo(context: Context, millis: Long): String {
    val elapsedTime = Date().time - millis

    var content = ""

    val years = elapsedTime / YEAR
    val weeks = elapsedTime / WEEK % WEEKS_IN_A_YEAR
    val days = (elapsedTime / DAY  % DAYS_IN_A_YEAR) % DAYS_IN_A_WEEK
    val hours = elapsedTime / HOUR % HOURS_IN_A_DAY
    val minutes = elapsedTime / MINUTE % MINUTES_IN_AN_HOUR
    val seconds = elapsedTime / SECOND % SECONDS_IN_A_MINUTE

    if (years > 0L) content += context.getString(R.string.simple_years_ago, years) + " "

    if (weeks > 0L) content += context.getString(R.string.simple_weeks_ago, weeks) + " "

    if (days > 0L) content += context.getString(R.string.simple_days_ago, days) + " "

    if (hours > 0L) content += context.getString(R.string.simple_hours_ago, hours) + " "

    if (minutes > 0L) content += context.getString(R.string.simple_minutes_ago, minutes) + " "

    if (seconds > 0L) content += context.getString(R.string.simple_seconds_ago, seconds)

    return content.trim()
}
