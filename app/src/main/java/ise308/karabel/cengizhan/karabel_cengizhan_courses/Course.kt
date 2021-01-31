package ise308.karabel.cengizhan.karabel_cengizhan_courses

class Course {

    var dbId : Int ?= null
    var courseCode : String ?= null //CMPE325
    var courseName : String ?= null  //Program Languages
    var courseGrade : String? = null //AA
    var courseCredit : Int ?= null  //6
    var isPassed : Boolean ?= null


    constructor(dbId: Int?, code: String?, name: String?, grade: String, credit: Int,  isPassed: Boolean?){
        this.dbId = dbId
        this.courseCode = code
        this.courseName = name
        this.courseGrade = grade
        this.courseCredit = credit
        this.isPassed = isPassed
    }
    constructor()
}

