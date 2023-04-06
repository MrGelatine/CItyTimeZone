package com.mrgelatine.citytimezones

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.ui.AppBarConfiguration
import com.mrgelatine.citytimezones.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        val viewModel: CountryViewModel by viewModels()
        viewModel.data.observe(this, Observer { item ->
            viewModel.adapter.setData(item)
        })
        binding.adapter = viewModel.adapter

    }
}