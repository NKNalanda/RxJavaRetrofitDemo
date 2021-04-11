package com.example.rxjavaretrofitdemo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.rxjavaretrofitdemo.R
import com.example.rxjavaretrofitdemo.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private var mainActivityViewModel: MainActivityViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel?.getPopularMovies()
    }
}