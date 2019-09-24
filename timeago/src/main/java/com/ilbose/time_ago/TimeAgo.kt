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

fun Date.dateTimeAgo(context: Context) = dateTimeAgo(context, time)

fun Calendar.dateTimeAgo(context: Context) = dateTimeAgo(context, timeInMillis)

fun dateTimeAgo(context: Context, millis: Long): String {

    val now = Calendar.getInstance()
    val date = Calendar.getInstance()
    date.timeInMillis = millis

    val years = now.get(Calendar.YEAR) - date.get(Calendar.YEAR)
    val months = now.get(Calendar.MONTH) - date.get(Calendar.MONTH)
    val weeks = now.get(Calendar.WEEK_OF_YEAR) - date.get(Calendar.WEEK_OF_YEAR)
    val days = now.get(Calendar.DAY_OF_YEAR) - date.get(Calendar.DAY_OF_YEAR)
    val hours = now.get(Calendar.HOUR_OF_DAY) - date.get(Calendar.HOUR_OF_DAY)
    val minutes = now.get(Calendar.MINUTE) - date.get(Calendar.MINUTE)
    val seconds = now.get(Calendar.SECOND) - date.get(Calendar.SECOND)

    return when {
        years == 1 -> context.getString(R.string.one_year_ago)
        years > 1 -> context.getString(R.string.years_ago, years)

        months == 1 -> context.getString(R.string.one_month_ago)
        months > 1 -> context.getString(R.string.months_ago, months)

        weeks == 1 -> context.getString(R.string.one_week_ago)
        weeks > 1 -> context.getString(R.string.weeks_ago, weeks)

        days == 1 -> context.getString(R.string.one_day_ago)
        days > 1 -> context.getString(R.string.days_ago, days)

        hours == 1 -> context.getString(R.string.one_hour_ago)
        hours > 1 -> context.getString(R.string.hours_ago, hours)

        minutes == 1 -> context.getString(R.string.one_minute_ago)
        minutes > 1 -> context.getString(R.string.minutes_ago, minutes)

        seconds < 5 -> context.getString(R.string.just_now)
        seconds >= 5 -> context.getString(R.string.seconds_ago, seconds)

        else -> timeAgo(context, millis)
    }
}
