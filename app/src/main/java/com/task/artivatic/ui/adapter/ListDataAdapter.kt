package com.task.artivatic.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.task.artivatic.R
import com.task.artivatic.databinding.LayoutDataBinding
import com.task.artivatic.extension.loadImage
import com.task.artivatic.extension.viewGone
import com.task.artivatic.extension.viewShow
import com.task.artivatic.pojo.ExampleData
import com.task.artivatic.listener.EventListener


class ListDataAdapter(
    private val mContext: Context,
    private val list: ArrayList<ExampleData.Row>?,
    private val mEventListener: EventListener<ExampleData.Row>
) : ArrayAdapter<ExampleData.Row>(
    mContext, R.layout.layout_data,
    list!!
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent);
    }

    @Nullable
    override fun getItem(position: Int): ExampleData.Row? {
        return super.getItem(position)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        val holder: ListDataViewHolder
        if (convertView == null) {
            val itemBinding: LayoutDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_data,
                parent,
                false
            )
            holder = ListDataViewHolder(itemBinding)
            holder.view = itemBinding.root
            holder.view.tag = holder
        } else {
            holder = convertView.tag as ListDataViewHolder
        }
        val item = getItem(position)
        Log.e("dataList", Gson().toJson(item))
        holder.binding.apply {
            item?.apply {
                if (title != null) {
                    textViewTitle.viewShow()
                    textViewTitle.text = title
                } else
                    textViewTitle.viewGone()
                if (description != null) {
                    textViewDesc.viewShow()
                    textViewDesc.text = description
                } else
                    textViewDesc.viewGone()
                imageView.loadImage(imageHref)
            }
            root.setOnClickListener {
                if (item != null) {
                    mEventListener.onItemClick(position, item, it)
                }
            }
        }

        return holder.view
    }

    private class ListDataViewHolder constructor(binding: LayoutDataBinding) {
        var view: View
        val binding: LayoutDataBinding

        init {
            view = binding.root
            this.binding = binding
        }
    }
}