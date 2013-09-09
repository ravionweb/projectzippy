package com.schooltrix.hibernate;



/**
 * SectionTeacherMap entity. @author MyEclipse Persistence Tools
 */

public class SectionTeacherMap  implements java.io.Serializable {


    // Fields    

     private Long stmId;
     private Long scmId;
     private Long umId;


    // Constructors

    /** default constructor */
    public SectionTeacherMap() {
    }

    
    /** full constructor */
    public SectionTeacherMap(Long scmId, Long umId) {
        this.scmId = scmId;
        this.umId = umId;
    }

   
    // Property accessors

    public Long getStmId() {
        return this.stmId;
    }
    
    public void setStmId(Long stmId) {
        this.stmId = stmId;
    }

    public Long getScmId() {
        return this.scmId;
    }
    
    public void setScmId(Long scmId) {
        this.scmId = scmId;
    }

    public Long getUmId() {
        return this.umId;
    }
    
    public void setUmId(Long umId) {
        this.umId = umId;
    }
   








}