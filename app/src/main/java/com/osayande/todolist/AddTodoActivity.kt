package com.osayande.todolist

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import com.osayande.todolist.business.Task
import kotlin.random.Random

class AddTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)
        val titletxt = findViewById<EditText>(R.id.titleeditext)
        val desctxt = findViewById<EditText>(R.id.descEditText)
        val submitbtn = findViewById<Button>(R.id.submit)

        submitbtn.setOnClickListener {
            if (TextUtils.isEmpty(titletxt.text) || TextUtils.isEmpty(desctxt.text)) { //checking if the word is empty
                Toast.makeText(this, "Text cannot be empty", Toast.LENGTH_LONG).show()
            } else {
                var title = titletxt.text
                var desc = desctxt.text
                val replyIntent = Intent()//Declaring an intent
                replyIntent.putExtra("title", title.toString()) // saving word to retrieve later
                replyIntent.putExtra("desc", desc.toString())
                setResult(Activity.RESULT_OK, replyIntent) //setting result and intent
                finish()
            }
        }

    }
}