package ise308.karabel.cengizhan.karabel_cengizhan_courses

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqlDataBase (context: Context) : SQLiteOpenHelper(context, "CourseDataBase", null, 1){

    companion object{
        val dbId = "id"
        val courseCode= "code"
        val courseName= "name"
        val courseGrade = "grade"
        val courseCredit = "credit"
        val isPassed = "passed"
    }
    val tableName="course"


    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $tableName(" +
                "$dbId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$courseCode VARCHAR(10), $courseName VARCHAR(20), " +
                "$courseGrade VARCHAR(2), $courseCredit Int(1) , $isPassed Int )"
        db!!.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun addToCourse(course:Course){
        val db = this.writableDatabase
        val isPassd = if(course.isPassed!!){1}else{0}
        val addDb = "INSERT INTO $tableName($courseCode,$courseName,$courseGrade,$courseCredit,$isPassed)" +
                " VALUES('${course.courseCode}','${course.courseName}','${course.courseGrade}','${course.courseCredit}','$isPassd')"
        db.execSQL(addDb)
    }

    fun removeCourse(course: Int){
        val db = this.writableDatabase
        val removeCourse = "DELETE FROM $tableName WHERE $dbId = $course"
        db.execSQL(removeCourse)

    }

    fun updateCourseInfo(course : Course){
        val db = this.writableDatabase
        val isPassedD = if(course.isPassed!!){1}else{0}

        val updateDataBase = "UPDATE $tableName SET $courseCode = '${course.courseCode}'," +
                "$courseName = '${course.courseName}' , $courseGrade = '${course.courseGrade}', " +
                "$courseCredit = '${course.courseCredit}', $isPassed = '$isPassedD' " +
                "WHERE $dbId = ${course.dbId}"
        db.execSQL(updateDataBase)
    }


    fun dBCourse():ArrayList<Course>{
        val db = this.readableDatabase
        val courses = ArrayList<Course>()
        val sql = "SELECT * FROM $tableName"
        val readyToDb = db.rawQuery(sql,null)
        if(readyToDb.moveToFirst()){
            while(!readyToDb.isAfterLast){
                var course = Course()
                course.dbId = readyToDb.getInt(readyToDb.getColumnIndex(dbId))
                course.courseCode = readyToDb.getString(readyToDb.getColumnIndex(courseCode))
                course.courseName = readyToDb.getString(readyToDb.getColumnIndex(courseName))
                course.courseGrade = readyToDb.getString(readyToDb.getColumnIndex(courseGrade))
                course.courseCredit = readyToDb.getInt(readyToDb.getColumnIndex(courseCredit))
                course.isPassed = readyToDb.getInt(readyToDb.getColumnIndex(isPassed))==1
                courses.add(course)
                readyToDb.moveToNext()
            }
        }
        return courses
    }

    fun closeDB(){
        this.close()
    }
}


