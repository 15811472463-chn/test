package com.yangchangwei.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var amountTv: TextView? = null
    private var timeTv: TextView? = null
    private var amountEd: EditText? = null
    private var timeEd: EditText? = null
    private var submitTv:TextView?=null
    private var amount:Double=0.0
    private var seconds:Long=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initListeners()

    }

    private fun initListeners() {
        amountEd?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                setAmountTvText(s)
            }

        })

        timeEd?.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                setTimeTvText(s)
            }

        })

        submitTv?.setOnClickListener {
            val result=amount*seconds;
            resultList.add(result);
            startActivity(Intent(this,ResultActivity::class.java))
            Log.e("YCW","submit==($amount*$seconds)===result==${result};${resultList.toString()}")
        }

    }

    private fun setTimeTvText(s: Editable?) {
        if (s?.toString()?.isNotEmpty() == true) {
            val num=s?.toString()?.toLong()
            if (num != null) {
                seconds=num
            }
            if (num != null) {
                timeTv?.text=Tools.millisToStr0(num*1000)
            }
        } else {
            timeTv?.text = "0s"

        }

    }

    private fun setAmountTvText(s: Editable?) {
        if (s?.toString()?.isNotEmpty() == true) {
            amountTv?.text = s?.toString()
            amount=s?.toString()?.toDouble()?:0.0
        } else {
            amountTv?.text = ""
            amount=0.0

        }
    }

    private fun initViews() {
        amountTv = findViewById(R.id.amount_tv)
        timeTv = findViewById(R.id.timenum_tv)
        amountEd = findViewById(R.id.amount_ed)
        timeEd = findViewById(R.id.time_ed)
        submitTv=findViewById(R.id.submit_tv)
    }


    companion object{
        var resultList= ArrayList<Double>()

    }


}