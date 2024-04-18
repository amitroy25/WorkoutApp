package com.example.a7minuteworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.a7minuteworkoutapp.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var binding: ActivityExerciseBinding?=null
    private var restTimer:CountDownTimer?=null
    private var restProgress=0
    private var excerciseTimer:CountDownTimer?=null
    private var excericseProgress=0
    private var exerciseList: ArrayList<ExerciseModel>?=null
    private var currentExercisePosition=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBarExercise)
        if(supportActionBar!=null)
        {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

        }
        exerciseList=Constants.defaultExerciseList()
        binding?.toolBarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
        setUpRestView()
    }
    private fun setUpRestView(){
        binding?.flRestView?.visibility= View.VISIBLE
        binding?.tvTitle?.visibility=View.VISIBLE
        binding?.tvExerciseName?.visibility=View.INVISIBLE
        binding?.flExerciseView?.visibility=View.INVISIBLE
        binding?.ivImage?.visibility=View.INVISIBLE
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0

        }
        setRestProgressBar()
    }
    private fun setUpExerciseView(){
        binding?.flRestView?.visibility= View.INVISIBLE
        binding?.tvTitle?.visibility=View.INVISIBLE
        binding?.tvExerciseName?.visibility=View.VISIBLE
        binding?.flExerciseView?.visibility=View.VISIBLE
        binding?.ivImage?.visibility=View.VISIBLE
        if(excerciseTimer!=null){
            excerciseTimer?.cancel()
            excericseProgress=0
        }
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text=exerciseList!![currentExercisePosition].getName()
        setExerciseProgressBar()

    }
    private fun setRestProgressBar(){
        binding?.progressBar?.progress=restProgress

        restTimer=object:CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {


                restProgress++
                binding?.progressBar?.progress=10-restProgress
                binding?.tvTimer?.text=(10-restProgress).toString()


            }

            override fun onFinish() {
                currentExercisePosition++

                setUpExerciseView()

            }

        }.start()
    }
    private fun setExerciseProgressBar(){
        binding?.progressBarExcercise?.progress=excericseProgress


        excerciseTimer=object:CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {


                excericseProgress++
                binding?.progressBarExcercise?.progress=30-excericseProgress
                binding?.tvTimerExcercise?.text=(30-excericseProgress).toString()


            }

            override fun onFinish() {

                       if(currentExercisePosition<exerciseList?.size!!-1){
                           setUpRestView()

                       }
                else{
                    Toast.makeText(this@ExerciseActivity,"Congratulations! You have Completed the 7 Minutes Workout",Toast.LENGTH_SHORT).show()
                       }
            }

        }.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0

        }
        if(excerciseTimer!=null){
            excerciseTimer?.cancel()
            excericseProgress=0
        }
        binding=null
    }
}