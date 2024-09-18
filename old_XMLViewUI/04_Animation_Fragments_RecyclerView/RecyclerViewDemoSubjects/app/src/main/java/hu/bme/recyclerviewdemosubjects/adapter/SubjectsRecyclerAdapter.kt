package hu.bme.recyclerviewdemosubjects.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.recyclerviewdemosubjects.R
import hu.bme.recyclerviewdemosubjects.data.Subject

class SubjectsRecyclerAdapter : RecyclerView.Adapter<SubjectsRecyclerAdapter.ViewHolder> {

    private val context: Context

    private val subjects: MutableList<Subject> = mutableListOf<Subject>(
        Subject("BSZ", "IB28", "Hetfo 12"),
        Subject("Analízis", "QII", "Kedd 12"),
        Subject("MobWeb", "QI", "Szerda 14"),
        Subject("MobWeb", "QI", "Szerda 14"),
        Subject("MobWeb", "QI", "Szerda 14"),
        Subject("MobWeb", "QI", "Szerda 14")
    )

    constructor(context: Context) : super() {
        this.context = context
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.subject_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subject = subjects[holder.adapterPosition]

        holder.tvName.text = subject.name
        holder.tvRoom.text = subject.room
        holder.tvTime.text = subject.time
    }

    override fun getItemCount(): Int {
        return subjects.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvRoom = itemView.findViewById<TextView>(R.id.tvRoom)
        val tvTime = itemView.findViewById<TextView>(R.id.tvTime)
    }


}