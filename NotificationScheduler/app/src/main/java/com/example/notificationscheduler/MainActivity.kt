package com.example.notificationscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
    }

    fun scheduleJob(view: View) {

        // Get the selected network option
        val selectedNetworkId = rg_network_options.checkedRadioButtonId
        var selectedNetworkOption = when(selectedNetworkId) {
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

        val constraintSet = (selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE) or sw_charging.isChecked or sw_idle.isChecked
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
