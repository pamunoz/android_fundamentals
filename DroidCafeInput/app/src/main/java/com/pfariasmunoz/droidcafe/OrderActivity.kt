package com.pfariasmunoz.droidcafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_order.*
import android.widget.ArrayAdapter



class OrderActivity : AppCompatActivity() , AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        val order = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        tv_order.text = order
        sp_label?.apply {
            onItemSelectedListener = this@OrderActivity
            // Create ArrayAdapter using the string array and default spinner layout.
            adapter = ArrayAdapter.createFromResource(this@OrderActivity, R.array.labels_array, android.R.layout.simple_spinner_item)
                // Specify the layout to use when the list of choices appears.
                .apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
        }

    }

    fun onRadioButtonClicked(view: View) {
        when(view.id) {
            R.id.rb_sameday -> if((view as RadioButton).isChecked) toast(R.string.rb_sameday_option)
            R.id.rb_nextday -> if((view as RadioButton).isChecked) toast(R.string.rb_nextday_option)
            R.id.rb_pickup -> if((view as RadioButton).isChecked) toast(R.string.rb_pickup_option)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
        toast(adapterView?.getItemAtPosition(position).toString())
    }
}
