package com.pfariasmunoz.scorekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.IntegerRes
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun increaseScore(view: View) {
        mScore++
        setScoreText(view.id)
    }
    fun decreaseScore(view: View) {
        mScore--
        setScoreText(view.id)
    }

    /**
     * Set the text of the view according to the id.
     */
    private fun setScoreText(@IntegerRes id: Int) {
        when(id) {
            R.id.btn_inc_score_team_1 -> {
                tv_score_team_1.text = mScore.toString()
            }
            R.id.btn_inc_score_team_2 -> {
                tv_score_team_2.text = mScore.toString()
            }
            R.id.btn_dec_score_team_1 -> {
                tv_score_team_1.text = mScore.toString()
            }
            R.id.btn_dec_score_team_2 -> {
                tv_score_team_2.text = mScore.toString()
            }
        }
    }
}
