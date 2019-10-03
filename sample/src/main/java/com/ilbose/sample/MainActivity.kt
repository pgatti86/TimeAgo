package com.ilbose.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ilbose.time_ago.fullTimeAgo
import com.ilbose.time_ago.simpleTimeAgo
import com.ilbose.time_ago.timeAgo
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Created by riccardo on 2019-09-20.
 */

class MainActivity : AppCompatActivity() {
    private val calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        time_picker.setOnTimeChangedListener { _, hour, minute ->

            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)

            time_ago.text = calendar.timeAgo(this)
            simple_time_ago.text = calendar.simpleTimeAgo(this)
            full_time_ago.text = calendar.fullTimeAgo(this)
        }

        date_picker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->

            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            time_ago.text = calendar.timeAgo(this)
            simple_time_ago.text = calendar.simpleTimeAgo(this)
            full_time_ago.text = calendar.fullTimeAgo(this)
        }
    }
}