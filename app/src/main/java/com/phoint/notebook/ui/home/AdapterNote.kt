package com.phoint.notebook.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.databinding.ItemNoteBookBinding
import com.phoint.notebook.util.setOnSingClickListener

class AdapterNote(private var listNote : List<NoteBook>) : RecyclerView.Adapter<AdapterNote.NoteBookViewHolder>() {
    inner class NoteBookViewHolder constructor(val binding : ItemNoteBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bin(noteBook: NoteBook){
            binding.notebook = noteBook
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteBookViewHolder {
        val binding = ItemNoteBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteBookViewHolder(binding)
    }

    var itemOnClick : ((NoteBook?) -> Unit)? = null
    var itemOnClickDelect : ((NoteBook) -> Unit)? = null

    fun setData(newData : List<NoteBook>){
        listNote = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NoteBookViewHolder, position: Int) {
        holder.bin(listNote[position])

        holder.itemView.setOnSingClickListener {
            itemOnClick?.invoke(listNote[position])
        }

        holder.binding.imgDelete.setOnSingClickListener {
            itemOnClickDelect?.invoke(listNote[position])
        }

    }

    override fun getItemCount(): Int = if (listNote.isNotEmpty()) listNote.size else 0
}