package com.example.notepadshared

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notepadshared.databinding.ActivityMainBinding
import com.example.notepadshared.fragments.FirstFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, FirstFragment()).commit()
    }
}