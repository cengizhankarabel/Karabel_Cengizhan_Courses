package ise308.karabel.cengizhan.karabel_cengizhan_courses

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CourseAdapter (var context: Context, var activity: Activity)
    : RecyclerView.Adapter<CourseAdapter.ListItemHolder>() {


    private val courses: ArrayList<Course> = ArrayList()
    private val inflate: LayoutInflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)


    inner class ListItemHolder(var view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var course: Course
        private var courseCodeText : TextView = view.findViewById(R.id.courseCodeText)
        private var courseNameText : TextView = view.findViewById(R.id.courseNameText)
        private var courseGradeText :TextView = view.findViewById(R.id.courseGradeText)
        private var courseCreditText : TextView = view.findViewById(R.id.courseCreditText)
        private var buttonEdit : ImageButton = view.findViewById(R.id.editButton)
        private var buttonDelete : ImageButton =view.findViewById(R.id.deleteButton)



        private val buttonEditClick = View.OnClickListener {
            val updateCourse = UpdateCourse(course)
            updateCourse.show((activity as MainActivity).supportFragmentManager, null)

        }

        private val buttonDeleteClick = View.OnClickListener{
            val builder = AlertDialog.Builder(activity)
            builder.setMessage("Do you really want to delete? \n \n    '${course.courseCode}' ").setPositiveButton( "Yes"){ _ , _ ->
                val sql = SqlDataBase(context)
                sql.removeCourse(course.dbId!!)
                (activity as MainActivity).updateCourseList()
            }.setNegativeButton( "No"){ dialog, _ ->
                dialog.dismiss()
            }.show()
        }



        init {

            buttonEdit.setOnClickListener(buttonEditClick)
            buttonDelete.setOnClickListener(buttonDeleteClick)

        }



        fun setCourse(courseObj: Course) {

            course = courseObj

            courseCodeText.text = course.courseCode
            courseNameText.text = course.courseName
            courseGradeText.text = course.courseGrade
            courseCreditText.text = course.courseCredit.toString()

            if (courseObj.isPassed!!) {
                view.setBackgroundColor(0xF01638E3.toInt())
            } else {
                view.setBackgroundColor(0xF2E9251E.toInt())

            }
        }

 }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        val view: View = inflate.inflate(R.layout.item_layout, parent, false)
        return ListItemHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        holder.setCourse(courses[position])
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    fun addNewCourse(course: Course){
        courses.add(course)
        this.notifyDataSetChanged()
    }

    fun removeCourse() {
        this.courses.clear()
        this.notifyDataSetChanged()
    }

//

}



