/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;

public class TaskAssignment {
    private String userId;
    private String taskId;
    private String jobDesc;

    // Constructors, getters, and setters
    public TaskAssignment(String userId, String taskId, String jobDesc) {
        this.userId = userId;
        this.taskId = taskId;
        this.jobDesc = jobDesc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    
}

