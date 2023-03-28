package com.phoint.notebook.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.*
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.phoint.notebook.R
import com.phoint.notebook.databinding.LayoutActionBarBinding
import com.phoint.notebook.util.setOnSingClickListener

@SuppressLint("ResourceType")
class ActionBarViewBinding(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
    private val binding : LayoutActionBarBinding
    private var menu: Menu? = null
    var onMenuItemClickListener: OnMenuItemClickListener? = null
    init {

        binding = LayoutActionBarBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
        val typeArray = context?.obtainStyledAttributes(attrs, R.styleable.ActionBarViewBinding)
        val menuResId = typeArray?.getResourceId(R.styleable.ActionBarViewBinding_action_bar_menu, -1)
        if (menuResId != -1) {
            if (menuResId != null) {
                binding.toolBar.inflateMenu(menuResId)
            }
            menu = binding.toolBar.menu

            val mnuTTND = menu?.findItem(R.id.mnuTTND)
            mnuTTND?.setOnMenuItemClickListener {
                onMenuItemClickListener?.onMenuItemClick(it)
                true
            }

            val mnuDangXuat = menu?.findItem(R.id.mnuDangXuat)
            mnuDangXuat?.setOnMenuItemClickListener {
                onMenuItemClickListener?.onMenuItemClick(it)
                true
            }

            val mnuThoat = menu?.findItem(R.id.mnuThoat)
            mnuThoat?.setOnMenuItemClickListener {
                onMenuItemClickListener?.onMenuItemClick(it)
                true
            }
        }

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

        val enableLeft = typeArray?.getBoolean(R.styleable.ActionBarViewBinding_action_bar_enable_src_left, false) ?: false
        binding.imgLeft.visibility = if (enableLeft) View.VISIBLE else View.GONE

        val enableRight = typeArray?.getBoolean(R.styleable.ActionBarViewBinding_action_bar_enable_src_right, false) ?: false
        binding.imgRight.visibility = if (enableRight) View.VISIBLE else View.GONE

        typeArray?.recycle()
    }

    fun setOnDateTitle(date : String){
       binding.tvTitle.text = date
    }

    fun setOnDateClick(callBack: (() -> Unit)){
        binding.tvTitle.setOnSingClickListener {
            callBack.invoke()
        }
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

    interface OnMenuItemClickListener {
        fun onMenuItemClick(menuItem: MenuItem): Boolean
    }
}
