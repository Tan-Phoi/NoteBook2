package com.phoint.notebook.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.phoint.notebook.R
import com.phoint.notebook.databinding.LayoutActionBarBinding
import com.phoint.notebook.util.setOnSingClickListener

class ActionBarViewBinding(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
    private val binding : LayoutActionBarBinding
    init {
        binding = LayoutActionBarBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)

        val typeArray = context?.obtainStyledAttributes(attrs, R.styleable.ActionBarViewBinding)

        val title = typeArray?.getString(R.styleable.ActionBarViewBinding_action_bar_title) ?: ""
        binding.tvTitle.text = title

        val imgLeft = typeArray?.getResourceId(R.styleable.ActionBarViewBinding_action_bar_src_left, -1) ?: -1
        if (imgLeft != -1){
            binding.imgLeft.setImageDrawable(ContextCompat.getDrawable(context!!, imgLeft))
        }

        val imgRight = typeArray?.getResourceId(R.styleable.ActionBarViewBinding_action_bar_src_left, -1) ?: -1
        if (imgRight != -1){
            binding.imgRight.setImageDrawable(ContextCompat.getDrawable(context!!, imgRight))
        }

        typeArray?.recycle()
    }

    fun setOnClickSrcLeft(callBack : (() -> Unit)){
        binding.imgLeft.setOnSingClickListener {
            callBack.invoke()
        }
    }

    fun setOnClickSrcRight(callBack: (() -> Unit)){
        binding.imgRight.setOnSingClickListener {
            callBack.invoke()
        }
    }
}