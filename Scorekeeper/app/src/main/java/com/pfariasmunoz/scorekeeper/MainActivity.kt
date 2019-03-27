package com.pfariasmunoz.scorekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mScore1: Int = 0
    private var mScore2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recoverInstanceState(savedInstanceState)
    }

    fun increaseScore(view: View) {
        setScoreText(view.id)
    }
    fun decreaseScore(view: View) {
        setScoreText(view.id)
    }

    /**
     * Set the text of the view according to the id.
     */
    private fun setScoreText(@IntegerRes id: Int) {
        when(id) {
            R.id.btn_inc_score_team_1 -> {
                mScore1++
                tv_score_team_1.text = mScore1.toString()
            }
            R.id.btn_inc_score_team_2 -> {
                mScore2++
                tv_score_team_2.text = mScore1.toString()
            }
            R.id.btn_dec_score_team_1 -> {
                mScore1--
                tv_score_team_1.text = mScore2.toString()
            }
            R.id.btn_dec_score_team_2 -> {
                mScore2--
                tv_score_team_2.text = mScore2.toString()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        // Change the label of the menu based on the state of the app.
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu?.findItem(R.id.night_mode)?.setTitle(R.string.day_mode)
        } else{
            menu?.findItem(R.id.night_mode)?.setTitle(R.string.night_mode)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //Check if the correct item was clicked
        if(item?.itemId == R.id.night_mode) {
            // Get the night mode state of the app
            val nightMode = AppCompatDelegate.getDefaultNightMode()
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            // Recreate the activity for the theme change to take effect.
            recreate()
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        // Save the scores
        outState?.apply {
            putInt(STATE_SCORE_1, mScore1)
            putInt(STATE_SCORE_2, mScore2)
        }
        super.onSaveInstanceState(outState)
    }

    fun recoverInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1)
            tv_score_team_1.text = mScore1.toString()
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2)
            tv_score_team_2.text = mScore2.toString()
        }
    }

    companion object {
        const val STATE_SCORE_1 = "Team 1 Score"
        const val STATE_SCORE_2 = "Team 2 Score"
    }
}
