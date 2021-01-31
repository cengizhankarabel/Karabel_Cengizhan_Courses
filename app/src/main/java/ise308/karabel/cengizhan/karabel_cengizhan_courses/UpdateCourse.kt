package ise308.karabel.cengizhan.karabel_cengizhan_courses

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment


class UpdateCourse (var course: Course) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        val view = activity!!.layoutInflater.inflate(R.layout.update_fragment,null)

        val courseCode = view.findViewById<EditText>(R.id.editCourseCode)
        val courseName = view.findViewById<EditText>(R.id.editCourseName)
        val courseGrade = view.findViewById<EditText>(R.id.editCourseGrade)
        val courseCredit = view.findViewById<EditText>(R.id.editCourseCredit)
        val isPassed = view.findViewById<CheckBox>(R.id.editIsPassed)

        val updateButton = view.findViewById<Button>(R.id.updateButton)


        courseCode.setText(course.courseCode)
        courseName.setText(course.courseName)
        courseCredit.setText(course.courseCredit.toString())
        courseGrade.setText(course.courseGrade)
        isPassed.isChecked = course.isPassed!!

        updateButton.setOnClickListener {

            if (courseCode.text.toString().isNotEmpty()) {
                if (courseName.text.toString().isNotEmpty()) {
                    if (courseCredit.text.toString().isNotEmpty()) {
                        if (courseGrade.text.toString().isNotEmpty()) {

                            if (courseCode.text.toString() != course.courseCode || courseName.text.toString() != course.courseName
                                || courseCredit.text.toString().toInt() != course.courseCredit || courseGrade.text.toString() != course.courseGrade
                                || isPassed.isChecked != course.isPassed) {

                                val sql = SqlDataBase(context!!)
                                sql.updateCourseInfo(
                                    Course(
                                        course.dbId,
                                        courseCode.text.toString(),
                                        courseName.text.toString(),
                                        courseGrade.text.toString(),
                                        courseCredit.text.toString().toInt(),

                                        isPassed.isChecked
                                    )
                                )
                                (activity as MainActivity).updateCourseList()
                                dismiss()
                            } else {

                                Toast.makeText(context, "No changed value", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Grade is empty", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Credit is empty", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Name is empty", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Code is empty", Toast.LENGTH_SHORT).show()
            }
        }


        builder.setView(view).setMessage(" ")
        return builder.create()
    }
}