package com.amazing.tengfei.kotlindemo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    lateinit var viewModel: MainViewModel

    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("countReserved", 0)
        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)

        lifecycle.addObserver(MyObserver())

//        refreshCounter();


        infoBt.setOnClickListener {
            viewModel.plusOne()
        }

        infoClear.setOnClickListener {
            viewModel.clear()
        }


        getUser.setOnClickListener {
            val userId = Math.random()
            viewModel.getUser(userId.toString())
        }

        viewModel.user.observe(this, Observer<User> {
            infoText.text = it.firstName
        })



    }

    override fun onPause() {
        super.onPause()
        sp.edit().putInt("countReserved", viewModel.counter.value ?: 0).apply()
    }

    private fun refreshCounter() {
        viewModel.counter.observe(this, Observer {
            infoText.text = it.toString()
        })

    }
}
