package com.osayande.todolist.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.osayande.todolist.DetailsActivity
import com.osayande.todolist.R
import com.osayande.todolist.ToDoViewModel
import com.osayande.todolist.business.Task
import com.osayande.todolist.databinding.TodoItemBinding

class ToDoRecyclerAdapter(
    private val arrayList: List<Task>, val context: Context,
    private val listener: OnItemClickedListener
) : RecyclerView.Adapter<ToDoRecyclerAdapter.NotesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: TodoItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.todo_item, parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(arrayList.get(position))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class NotesViewHolder(private val binding: TodoItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(task: Task) {
            binding.nametextview.text = task.title
            binding.desctextview.text = task.description

            if (task.complete) { //Updating the UI depending on the completed item
                binding.completetextview.visibility = View.VISIBLE
                binding.completetextview.text = "COMPLETED"
            } else {
                binding.completetextview.visibility = View.VISIBLE
                binding.completetextview.text = "INCOMPLETE"
            }

            binding.layoutItem.setOnClickListener { //When the user clicks a recyclerview item
                var replyIntent = Intent(context.applicationContext, DetailsActivity::class.java)
                replyIntent.putExtra("title", task.title) // saving word to retrieve later
                replyIntent.putExtra("desc", task.description)
                replyIntent.putExtra("id", task.id)
                context.startActivity(replyIntent)
            }

        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickedListener {
        fun onItemClick(position: Int)
    }
}