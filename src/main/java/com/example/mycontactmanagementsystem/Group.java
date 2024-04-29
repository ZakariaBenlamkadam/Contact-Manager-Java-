package com.example.mycontactmanagementsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Group {
    private static Integer IDgroup;
    private static String nomGroup;

    public Group(Integer IDgroup, String nom) {
        this.IDgroup = IDgroup;
        this.nomGroup = nom;

    }

    public Group() {

    }

    public static Integer getIDgroup() {
        return IDgroup;
    }

    public void setIDgroup(Integer IDgroup) {
        this.IDgroup = IDgroup;
    }

    public static String getNomGroup() {
        return nomGroup;
    }

    public void setNomGroup(String nomGroup) {
        this.nomGroup = nomGroup;
    }

    public static void displayAllGroups() {
        Connection connection = DatabaseConnection.connectDb();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM groupe";
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Integer IDgroup = resultSet.getInt("idGroup");
                    String nomGroup = resultSet.getString("Nom");

                    Group group = new Group(IDgroup, nomGroup);
                    System.out.println("IDgroup: " + group.getIDgroup() + ", nomGroup: " + group.getNomGroup());
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
