package com.osayande.todolist

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.osayande.todolist.recyclerview.ToDoRecyclerAdapter
import com.osayande.todolist.business.Task
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ToDoRecyclerAdapter.OnItemClickedListener {
   // private lateinit var binding: ActivityMainBinding

    private val todoViewModel: ToDoViewModel by viewModels {
        ToDoViewModelFactory((application as ToDoApplication).todorepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addbtn = findViewById<FloatingActionButton>(R.id.addnote)
        val recyclerView = findViewById<RecyclerView>(R.id.todosrecyclerview) //Declaring the recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)

        todoViewModel.allnotes.observe(this, Observer { words ->
            // Observing and updating UI with data
            words?.let {
                recyclerView.adapter= ToDoRecyclerAdapter(it, this, this)
            }
        })


        //allows to add a to do item in the database.
        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val intent = result.data
                    var title = intent?.getStringExtra("title")
                    var desc = intent?.getStringExtra("desc")
                    var timestamp = System.currentTimeMillis()

                    if (title != null && desc != null) {
                        var task = Task(title, desc, false, timestamp, getColor())
                        todoViewModel.insertNote(task)
                    } else {
                        Toast.makeText(this, "Cannot save empty word.", Toast.LENGTH_SHORT).show();
                    }

                }
        }

        addbtn.setOnClickListener {
            startForResult.launch(Intent(this, AddTodoActivity::class.java))
        }

    }

    private fun getColor(): Int {
        return Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    }

    override fun onItemClick(position: Int) {}

}

