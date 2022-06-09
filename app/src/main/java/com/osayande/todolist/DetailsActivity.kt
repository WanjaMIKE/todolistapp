package com.osayande.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.osayande.todolist.business.Task
import com.osayande.todolist.databinding.ActivityDetailBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding

    private val todoViewModel: ToDoViewModel by viewModels {
        ToDoViewModelFactory((application as ToDoApplication).todorepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        var title = intent?.getStringExtra("title")
        var description = intent?.getStringExtra("desc")
        var id = intent.getIntExtra("id",0)
        var color = intent.getIntExtra("color",0)
        binding.titletxtview.text = title
        binding.desctextview.text = description
        var task = Task(title.toString(), description.toString(), false,
                System.currentTimeMillis(), color, id)

        binding.deletebtn.setOnClickListener { //deleting from db
            todoViewModel.deleteNote(task)
            Toast.makeText(this,"Item deleted",Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.markasdonebtn.setOnClickListener { //updating the complete bool value as true
           todoViewModel.updateNote(
               Task(title.toString(), description.toString(), true, System.currentTimeMillis(), color, id))
        }

    }

}