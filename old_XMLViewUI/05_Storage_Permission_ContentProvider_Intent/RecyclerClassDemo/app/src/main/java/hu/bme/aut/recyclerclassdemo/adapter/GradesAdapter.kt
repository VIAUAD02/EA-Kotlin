package hu.bme.aut.recyclerclassdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.recyclerclassdemo.data.Grade
import hu.bme.aut.recyclerclassdemo.databinding.GradeRowBinding

class GradesAdapter : RecyclerView.Adapter<GradesAdapter.ViewHolder> {

    private val items = mutableListOf<Grade>()
    private val context: Context

    constructor(context: Context, itemsList: List<Grade>) : super() {
        this.context = context
        items.addAll(itemsList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val gradeRowBinding = GradeRowBinding.inflate(
            LayoutInflater.from(context),parent,false
        )
        return ViewHolder(gradeRowBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[holder.adapterPosition])
    }

    fun addItem(grade: Grade) {
        items.add(grade)
        //notifyDataSetChanged()
        notifyItemInserted(items.lastIndex)
    }

    inner class ViewHolder(val binding: GradeRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(grade: Grade) {
            binding.tvName.text = grade.studentName
            binding.tvSubject.text = grade.subject
            binding.tvGrade.text = "${grade.grade}"
        }
    }

}