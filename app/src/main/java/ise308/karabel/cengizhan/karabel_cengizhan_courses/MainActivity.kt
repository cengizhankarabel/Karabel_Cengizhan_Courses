package ise308.karabel.cengizhan.karabel_cengizhan_courses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var courseList: ArrayList<Course>
    lateinit var courseAdapter : CourseAdapter
    private lateinit var sql : SqlDataBase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        courseList = ArrayList()
        recyclerView = findViewById(R.id.recyclerView)
        courseAdapter = CourseAdapter(applicationContext, this)
        recyclerView.adapter = courseAdapter
        recyclerView.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL, false)
        sql = SqlDataBase(applicationContext)

        val courses = sql.dBCourse()
        for (i in courses) {
            courseAdapter.addNewCourse(i)
        }
    }

    fun updateCourseList(){
        courseAdapter.removeCourse()
        sql = SqlDataBase(applicationContext)
        val courses = sql.dBCourse()
        for(i in courses){
            courseAdapter.addNewCourse(i)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.createCourse) {
            startActivityForResult(Intent(applicationContext,NewCourse::class.java),1)
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if(data != null){
                courseAdapter.removeCourse()
                sql = SqlDataBase(applicationContext)
                val courses = sql.dBCourse()
                for(i in courses){
                    courseAdapter.addNewCourse(i)
                }
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        this.sql.closeDB()
    }

}
