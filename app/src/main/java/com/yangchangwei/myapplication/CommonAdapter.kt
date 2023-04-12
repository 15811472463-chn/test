package com.yangchangwei.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 *
 * @Author: yangchangwei
 * @Date: 2023/4/12 14:30
 * @Description:通用RecycleView adapter 避免在项目中写多个adapter
 */
abstract class CommonAdapter<T>(
    protected var mContext: Context,
    datas: MutableList<T>?,
    layoutId: Int
) : RecyclerView.Adapter<CommonViewHolder?>() {
    private var mLayoutId: Int = layoutId
    private var mDatas: MutableList<T>?=datas
    private var mInflater: LayoutInflater = LayoutInflater.from(mContext)
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mOnItemLongClickListener: OnItemLongClickListener? = null
    private val mOnItemChildClickListener: OnItemChildClickListener? = null
    private val mOnItemChildLongClickListener: OnItemChildLongClickListener? = null
    fun setmDatas(mDatas: MutableList<T>?) {
        this.mDatas = mDatas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        var commonViewHolder: CommonViewHolder? = null
        commonViewHolder = if (viewType != 0) {
            CommonViewHolder.createViewHolder(mContext, parent, getItemIdType(viewType))
        } else {
            CommonViewHolder.createViewHolder(mContext, parent, mLayoutId)
        }
        onViewHolderCreated(commonViewHolder, commonViewHolder.convertView, viewType)
        return commonViewHolder
    }


    override fun onBindViewHolder(holderCommon: CommonViewHolder, position: Int) {
        convert(holderCommon, mDatas!![position])
    }




    fun onViewHolderCreated(holderCommon: CommonViewHolder?, itemView: View?, viewType: Int) {
        bindViewClickListener(holderCommon, viewType)
    }

    protected fun bindViewClickListener(commonViewHolder: CommonViewHolder?, viewType: Int) {
        if (mOnItemClickListener != null) {
            commonViewHolder!!.itemView.setOnClickListener(View.OnClickListener { v ->
                val position = commonViewHolder.adapterPosition
                if (position == RecyclerView.NO_POSITION) {
                    return@OnClickListener
                }
                setOnItemClick(v, position)
            })
        }
        if (mOnItemLongClickListener != null) {
            commonViewHolder!!.itemView.setOnLongClickListener { v ->
                val position = commonViewHolder.adapterPosition
                if (position == RecyclerView.NO_POSITION) {
                    false
                } else setOnItemLongClick(v, position)
            }
        }
    }

    /**
     * override this method if you want to override click event logic
     *
     *
     * 如果你想重新实现 item 点击事件逻辑，请重写此方法
     *
     * @param v
     * @param position
     */
    protected fun setOnItemClick(v: View, position: Int) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener!!.onItemClick(this, v, position)
        }
    }

    /**
     * override this method if you want to override longClick event logic
     *
     *
     * 如果你想重新实现 item 长按事件逻辑，请重写此方法
     *
     * @param v
     * @param position
     * @return
     */
    protected fun setOnItemLongClick(v: View, position: Int): Boolean {
        return if (mOnItemLongClickListener == null) false else mOnItemLongClickListener!!.onItemLongClick(
            this,
            v,
            position)
    }

    fun getItemIdType(viewType: Int): Int {
        return viewType
    }

    abstract fun convert(holderCommon: CommonViewHolder?, t: T)
    override fun getItemCount(): Int {
        return if (mDatas == null) 0 else mDatas!!.size
    }

    fun setNewData(t: MutableList<T>?) {
        mDatas = t
        notifyDataSetChanged()
    }

    fun insertData(t: T) {
        mDatas!!.add(t)
        notifyDataSetChanged()
    }

    fun removeData(t: T) {
        val i = mDatas!!.indexOf(t)
        mDatas!!.remove(t)
        if (i > -1) notifyItemRemoved(i)
    }

    val data: List<T>
        get() = if (mDatas == null) ArrayList() else mDatas!!

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mOnItemClickListener = listener
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener?) {
        mOnItemLongClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(adapter: RecyclerView.Adapter<CommonViewHolder?>, view: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(
            adapter: RecyclerView.Adapter<CommonViewHolder?>,
            view: View,
            position: Int
        ): Boolean
    }

    interface OnItemChildClickListener {
        fun onItemChildClick(
            adapter: RecyclerView.Adapter<CommonViewHolder?>,
            view: View,
            position: Int
        )
    }

    interface OnItemChildLongClickListener {

        fun onItemChildLongClick(
            adapter: RecyclerView.Adapter<CommonViewHolder?>,
            view: View,
            position: Int
        ): Boolean
    }

    init {
        mDatas = datas
    }
}