package com.example.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel.viewmodel.CountViewModel
import kotlinx.android.synthetic.main.activity_main.*

class ViewmodelActivity : AppCompatActivity() {

    lateinit var countViewModel: CountViewModel
    private val sharedFile = "SHAREDCOUNT"
    var sharedPreferences: SharedPreferences? = null
    var editor : SharedPreferences.Editor? = null                                  //



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmodel)

        sharedPreferences = this.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        editor = sharedPreferences?.edit()

        countViewModel = ViewModelProvider(this).get(CountViewModel::class.java)


       // var count = sharedPreferences?.getInt("COUNT", 0)

        //zero.text = count.toString()

        var storedCount = sharedPreferences?.getInt("COUNT",0)
        Log.d("count", storedCount.toString())
        zero.text = storedCount.toString()


        button.setOnClickListener {
            Log.d("click", "clicked")
            Log.d("store count", storedCount.toString())
            var count = zero.text.toString().toInt()
            countViewModel.setCount(count)
            zero.text = countViewModel.getCount().toString()

        }


    }

    override fun onPause() {
        super.onPause()
        editor?.putInt("COUNT", countViewModel.getCount())
        editor?.apply()
    }
}