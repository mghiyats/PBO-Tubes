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
import model.Group;

public class GroupDAO {

    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `group`")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                groups.add(new Group(rs.getString("group_id"), rs.getString("user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    public Group getGroupById(String groupId) {
        Group group = null;
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `group` WHERE group_id = ?")) {
            stmt.setString(1, groupId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                group = new Group(rs.getString("group_id"), rs.getString("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public void addGroup(Group group) {
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement("INSERT INTO `group` (group_id, user_id) VALUES (?, ?)")) {
            stmt.setString(1, group.getGroupId());
            stmt.setString(2, group.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGroup(Group group) {
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement("UPDATE `group` SET user_id = ? WHERE group_id = ?")) {
            stmt.setString(1, group.getUserId());
            stmt.setString(2, group.getGroupId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGroup(String groupId) {
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement stmt = conn.prepareStatement("DELETE FROM `group` WHERE group_id = ?")) {
            stmt.setString(1, groupId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
