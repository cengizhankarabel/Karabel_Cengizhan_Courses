package ise308.karabel.cengizhan.karabel_cengizhan_courses

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class NewCourse : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_fragment)

        val courseCode = findViewById<EditText>(R.id.edit_courseCode)
        val courseName = findViewById<EditText>(R.id.edit_courseName)
        val courseGrade = findViewById<EditText>(R.id.edit_courseGrade)
        val courseCredit = findViewById<EditText>(R.id.edit_courseCredit)
        val isPassed = findViewById<CheckBox>(R.id.edit_isPassed)

        val addButton = findViewById<Button>(R.id.addNewCourse)

        addButton.setOnClickListener {
            if (courseCode.text.toString().isNotEmpty()) {
                if (courseName.text.toString().isNotEmpty()) {
                    if (courseGrade.text.toString().isNotEmpty()) {
                        if (courseCredit.text.toString().isNotEmpty()) {

                            val course = Course(
                                1,
                                courseCode.text.toString(),
                                courseName.text.toString(),
                                courseGrade.text.toString(),
                                courseCredit.text.toString().toInt(),
                                isPassed.isChecked)
                            val sql = SqlDataBase(applicationContext)
                            sql.addToCourse(course)
                            val intent = Intent()
                            intent.putExtra("isAdded", true)
                            setResult(1, intent)
                            finish()
                        } else {
                            Toast.makeText(applicationContext, "Course Credit is empty", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(applicationContext,"Course Grade is empty",Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(applicationContext,"Course Name is empty",Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(applicationContext,"Course Code is empty",Toast.LENGTH_SHORT).show()
            }
        }


    }
}