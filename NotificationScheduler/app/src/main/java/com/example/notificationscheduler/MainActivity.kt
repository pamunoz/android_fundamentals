package com.example.notificationscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mJobScheduler: JobScheduler? = null
    companion object {
        const val JOB_ID = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sb_deadline.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tv_seek_bar_progress.text = if (progress > 0) progress.toString() + "s" else "Not Set"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun scheduleJob(view: View) {



        // Get the selected network option
        val selectedNetworkId = rg_network_options.checkedRadioButtonId
        val selectedNetworkOption = when(selectedNetworkId) {
            R.id.rb_no_network -> JobInfo.NETWORK_TYPE_NONE
            R.id.rb_any_network -> JobInfo.NETWORK_TYPE_ANY
            R.id.rb_wifi_network -> JobInfo.NETWORK_TYPE_UNMETERED
            else -> JobInfo.NETWORK_TYPE_NONE
        }

        // Create the job scheduler and the job info object
        mJobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val serviceName = ComponentName(packageName, NotificationJobService::class.java.name)
        val builder = JobInfo.Builder(JOB_ID, serviceName).apply {
            setRequiredNetworkType(selectedNetworkOption)
            setRequiresDeviceIdle(sw_idle.isChecked)
            setRequiresCharging(sw_charging.isChecked)
        }

        // Check if the user has changed the value of the seek bar
        val seekBarSet = sb_deadline.progress > 0
        // Set a deadline according to the seek bar
        if (seekBarSet) builder.setOverrideDeadline(sb_deadline.progress * 1000L)

        val constraintSet = (selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE) or sw_charging.isChecked or sw_idle.isChecked or seekBarSet
        if (constraintSet) {
            val jobInfo: JobInfo = builder.build()
            mJobScheduler?.schedule(jobInfo)
            Toast.makeText(this, "Job Scheduled, job will run when " +
                    "the constraints are met.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please set at least one constraint", Toast.LENGTH_SHORT).show()
        }
    }

    fun cancelJobs(view: View) {
        if (mJobScheduler != null) {
            mJobScheduler?.cancelAll()
            mJobScheduler = null
            Toast.makeText(this, "Jobs cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
