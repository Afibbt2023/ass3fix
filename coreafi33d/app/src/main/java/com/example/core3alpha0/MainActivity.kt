package com.example.core3alpha0
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core3alpha0.Meeting
import com.example.core3alpha0.MeetingAdapter
import com.example.core3alpha0.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MeetingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val meetings = parseCSV()
        adapter = MeetingAdapter(meetings)
        recyclerView.adapter = adapter


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter_tech -> {
                adapter.filter("Completed")
                true
            }
            R.id.action_filter_tech2 -> {
                adapter.filter("In progress")
                true
            }
            R.id.action_filter_all -> {
                adapter.filter("All")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun parseCSV(): List<Meeting> {
        val inputStream = resources.openRawResource(R.raw.groups1)
        val lines = inputStream.bufferedReader().readLines()

        return lines.drop(1).map {  // Use drop(1) to skip the first line.
            val parts = it.split(",")
            Meeting(parts[0], parts[1], parts[2], parts[3], parts[4])
        }
    }
}
