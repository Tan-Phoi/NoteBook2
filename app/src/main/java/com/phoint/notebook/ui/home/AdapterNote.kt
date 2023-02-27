package com.phoint.notebook.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.data.local.model.User
import com.phoint.notebook.databinding.ItemNoteBookBinding

class AdapterNote(private val listNote : List<NoteBook>) : RecyclerView.Adapter<AdapterNote.NoteBookViewHolder>() {
    inner class NoteBookViewHolder constructor(private val binding : ItemNoteBookBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bin(noteBook: NoteBook){
            binding.notebook = noteBook
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteBookViewHolder {
        val binding = ItemNoteBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteBookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteBookViewHolder, position: Int) {
        holder.bin(listNote[position])
    }

    override fun getItemCount(): Int = if (listNote.isNotEmpty()) listNote.size else 0
}