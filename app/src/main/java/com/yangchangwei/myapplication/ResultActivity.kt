package com.yangchangwei.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecyclerListener

/**
 * @Author : XXX
 * @Date : 2023/4/12 7:16 PM
 * @Description :
 */
class ResultActivity : AppCompatActivity() {
    private var resultListView: RecyclerView? = null
    private var commonAdapter: CommonAdapter<Double>? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result);
        initView()


    }

    private fun initView() {
        Log.e("YCW","data="+MainActivity.resultList.toMutableList())
        resultListView = findViewById(R.id.result_lv)
        commonAdapter = object : CommonAdapter<Double>(
            this,
            MainActivity.resultList.toMutableList(),
            R.layout.itemview
        ) {
            override fun convert(holderCommon: CommonViewHolder?, t: Double) {
                val resultTv = holderCommon?.convertView?.findViewById<TextView>(R.id.result_tv)
                resultTv?.text = t.toString()
            }

        }
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        resultListView?.apply {
            layoutManager = linearLayoutManager
            adapter = commonAdapter

        }
        commonAdapter?.notifyDataSetChanged()
    }
}