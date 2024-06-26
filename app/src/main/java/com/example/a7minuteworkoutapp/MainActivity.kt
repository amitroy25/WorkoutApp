package com.example.a7minuteworkoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import com.example.a7minuteworkoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

            binding?.flStart?.setOnClickListener {
            Toast.makeText(this,"here we will start the exercise",Toast.LENGTH_SHORT).show()
                val intent= Intent(this,ExerciseActivity::class.java);
                startActivity(intent);//yhi hai
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding=null
    }
}