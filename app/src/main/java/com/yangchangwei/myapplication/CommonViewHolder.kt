package com.yangchangwei.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.util.Linkify
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @Author: yangchangwei
 * @Date: 2023/4/12 8:20
 * @Description:viewHolder
 */
class CommonViewHolder(private val mContext: Context, val convertView: View) : RecyclerView.ViewHolder(
    convertView) {
    private val mViews: SparseArray<View?> = SparseArray()
    var pos = 0

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    fun <T : View?> getView(viewId: Int): T? {
        var view = mViews[viewId]
        if (view == null) {
            view = convertView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as T?
    }
    /****以下为辅助方法 */
    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    fun setText(viewId: Int, text: String?): CommonViewHolder {
        val tv = getView<TextView>(viewId)!!
        tv.text = text
        return this
    }

    fun setText(viewId: Int, id: Int): CommonViewHolder {
        val tv = getView<TextView>(viewId)!!
        tv.setText(id)
        return this
    }

    fun setImageResource(viewId: Int, resId: Int): CommonViewHolder {
        val view = getView<ImageView>(viewId)!!
        view.setImageResource(resId)
        return this
    }

    fun setImageBitmap(viewId: Int, bitmap: Bitmap?): CommonViewHolder {
        val view = getView<ImageView>(viewId)!!
        view.setImageBitmap(bitmap)
        return this
    }

    fun setImageDrawable(viewId: Int, drawable: Drawable?): CommonViewHolder {
        val view = getView<ImageView>(viewId)!!
        view.setImageDrawable(drawable)
        return this
    }

    fun setBackgroundColor(viewId: Int, color: Int): CommonViewHolder {
        val view = getView<View>(viewId)!!
        view.setBackgroundColor(color)
        return this
    }

    fun setBackgroundRes(viewId: Int, backgroundRes: Int): CommonViewHolder {
        val view = getView<View>(viewId)!!
        view.setBackgroundResource(backgroundRes)
        return this
    }

    fun setTextColor(viewId: Int, textColor: Int): CommonViewHolder {
        val view = getView<TextView>(viewId)!!
        view.setTextColor(textColor)
        return this
    }

    fun setTextColorRes(viewId: Int, textColorRes: Int): CommonViewHolder {
        val view = getView<TextView>(viewId)!!
        view.setTextColor(mContext.resources.getColor(textColorRes))
        return this
    }

    @SuppressLint("NewApi")
    fun setAlpha(viewId: Int, value: Float): CommonViewHolder {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView<View>(viewId)!!.alpha = value
        } else {
            // Pre-honeycomb hack to set Alpha value
            val alpha = AlphaAnimation(value, value)
            alpha.duration = 0
            alpha.fillAfter = true
            getView<View>(viewId)!!.startAnimation(alpha)
        }
        return this
    }

    fun setVisible(viewId: Int, visible: Boolean): CommonViewHolder {
        val view = getView<View>(viewId)!!
        view.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }

    fun linkify(viewId: Int): CommonViewHolder {
        val view = getView<TextView>(viewId)!!
        Linkify.addLinks(view, Linkify.ALL)
        return this
    }

    fun setTypeface(typeface: Typeface?, vararg viewIds: Int): CommonViewHolder {
        for (viewId in viewIds) {
            val view = getView<TextView>(viewId)!!
            view.typeface = typeface
            view.paintFlags = view.paintFlags or Paint.SUBPIXEL_TEXT_FLAG
        }
        return this
    }

    fun setProgress(viewId: Int, progress: Int): CommonViewHolder {
        val view = getView<ProgressBar>(viewId)!!
        view.progress = progress
        return this
    }

    fun setProgress(viewId: Int, progress: Int, max: Int): CommonViewHolder {
        val view = getView<ProgressBar>(viewId)!!
        view.max = max
        view.progress = progress
        return this
    }

    fun setMax(viewId: Int, max: Int): CommonViewHolder {
        val view = getView<ProgressBar>(viewId)!!
        view.max = max
        return this
    }

    fun setRating(viewId: Int, rating: Float): CommonViewHolder {
        val view = getView<RatingBar>(viewId)!!
        view.rating = rating
        return this
    }

    fun setRating(viewId: Int, rating: Float, max: Int): CommonViewHolder {
        val view = getView<RatingBar>(viewId)!!
        view.max = max
        view.rating = rating
        return this
    }

    fun setTag(viewId: Int, tag: Any?): CommonViewHolder {
        val view = getView<View>(viewId)!!
        view.tag = tag
        return this
    }

    fun setTag(viewId: Int, key: Int, tag: Any?): CommonViewHolder {
        val view = getView<View>(viewId)!!
        view.setTag(key, tag)
        return this
    }

    fun setChecked(viewId: Int, checked: Boolean): CommonViewHolder {
        val view = getView<View>(viewId) as Checkable
        view.isChecked = checked
        return this
    }

    /**
     * 关于事件的
     */
    fun setOnClickListener(
        viewId: Int,
        listener: View.OnClickListener?
    ): CommonViewHolder {
        val view = getView<View>(viewId)!!
        view.setOnClickListener(listener)
        return this
    }

    fun setOnTouchListener(
        viewId: Int,
        listener: OnTouchListener?
    ): CommonViewHolder {
        val view = getView<View>(viewId)!!
        view.setOnTouchListener(listener)
        return this
    }

    fun setOnLongClickListener(
        viewId: Int,
        listener: OnLongClickListener?
    ): CommonViewHolder {
        val view = getView<View>(viewId)!!
        view.setOnLongClickListener(listener)
        return this
    }

    companion object {
        fun createViewHolder(
            context: Context,
            itemView: View
        ): CommonViewHolder {
            return CommonViewHolder(context, itemView)
        }

        fun createViewHolder(
            context: Context,
            parent: ViewGroup?, layoutId: Int
        ): CommonViewHolder {
            val itemView =
                LayoutInflater.from(context).inflate(layoutId, parent,
                    false)
            return CommonViewHolder(context, itemView)
        }

        fun createViewHolder(context: Context, layoutId: Int): CommonViewHolder {
            val itemView =
                LayoutInflater.from(context).inflate(layoutId, null)
            return CommonViewHolder(context, itemView)
        }
    }

}