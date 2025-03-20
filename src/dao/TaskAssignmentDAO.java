/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.TaskAssignment;

public class TaskAssignmentDAO {

    public List<TaskAssignment> getAllTaskAssignments() {
        List<TaskAssignment> taskAssignments = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM taskAssignment")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                taskAssignments.add(new TaskAssignment(rs.getString("user_id"), rs.getString("task_id"), rs.getString("jobdesc")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskAssignments;
    }

    public List<TaskAssignment> getTaskAssignmentsByTaskId(String taskId) {
        List<TaskAssignment> taskAssignments = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM taskAssignment WHERE task_id = ?")) {
            stmt.setString(1, taskId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                taskAssignments.add(new TaskAssignment(
                        rs.getString("user_id"),
                        rs.getString("task_id"),
                        rs.getString("jobdesc")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskAssignments;
    }

    public TaskAssignment getTaskAssignment(String userId, String taskId) {
        TaskAssignment taskAssignment = null;
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM taskAssignment WHERE user_id = ? AND task_id = ?")) {
            stmt.setString(1, userId);
            stmt.setString(2, taskId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                taskAssignment = new TaskAssignment(rs.getString("user_id"), rs.getString("task_id"), rs.getString("jobdesc"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskAssignment;
    }

    public void addTaskAssignment(TaskAssignment taskAssignment) {
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement("INSERT INTO taskAssignment (user_id, task_id, jobdesc) VALUES (?, ?, ?)")) {
            stmt.setString(1, taskAssignment.getUserId());
            stmt.setString(2, taskAssignment.getTaskId());
            stmt.setString(3, taskAssignment.getJobDesc());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTaskAssignment(TaskAssignment taskAssignment) {
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement("UPDATE taskAssignment SET jobdesc = ? WHERE user_id = ? AND task_id = ?")) {
            stmt.setString(1, taskAssignment.getJobDesc());
            stmt.setString(2, taskAssignment.getUserId());
            stmt.setString(3, taskAssignment.getTaskId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTaskAssignment(String userId, String taskId) {
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement("DELETE FROM taskAssignment WHERE user_id = ? AND task_id = ?")) {
            stmt.setString(1, userId);
            stmt.setString(2, taskId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
