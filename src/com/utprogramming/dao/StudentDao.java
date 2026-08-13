package com.utprogramming.dao;

import com.utprogramming.db.DBConnection;
import com.utprogramming.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentDao implements StudentDaoInterface {

    @Override
    public boolean insertStudent(Student s) {
        boolean flag = false;

        try {
            Connection con = DBConnection.createConnection();

            String query = "INSERT INTO student_details(sname, clgname, city, percentage) VALUES (?, ?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, s.getName());
            pst.setString(2, s.getClgName());
            pst.setString(3, s.getCity());
            pst.setDouble(4, s.getPercentage());

            int rows = pst.executeUpdate();

            if (rows > 0) {
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public boolean delete(int roll) {
        boolean flag = false;

        try {
            Connection con = DBConnection.createConnection();

            String query = "DELETE FROM student_details WHERE rollnum=?";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, roll);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public boolean update(int roll, String update, int ch, Student s) {

        boolean flag = false;

        try {
            Connection con = DBConnection.createConnection();

            String query = "";

            switch (ch) {

                case 1:
                    query = "UPDATE student_details SET sname=? WHERE rollnum=?";
                    break;

                case 2:
                    query = "UPDATE student_details SET clgname=? WHERE rollnum=?";
                    break;

                case 3:
                    query = "UPDATE student_details SET city=? WHERE rollnum=?";
                    break;

                case 4:
                    query = "UPDATE student_details SET percentage=? WHERE rollnum=?";
                    break;

                default:
                    System.out.println("Invalid Choice");
                    return false;
            }

            PreparedStatement pst = con.prepareStatement(query);

            if (ch == 4) {
                pst.setDouble(1, Double.parseDouble(update));
            } else {
                pst.setString(1, update);
            }

            pst.setInt(2, roll);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public void showAllStudent() {

        try {
            Connection con = DBConnection.createConnection();

            String query = "SELECT * FROM student_details";

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                System.out.println("Roll Number : " + rs.getInt("rollnum"));
                System.out.println("Name        : " + rs.getString("sname"));
                System.out.println("College     : " + rs.getString("clgname"));
                System.out.println("City        : " + rs.getString("city"));
                System.out.println("Percentage  : " + rs.getDouble("percentage"));
                System.out.println("--------------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean showStudentById(int roll) {

        boolean flag = false;

        try {
            Connection con = DBConnection.createConnection();

            String query = "SELECT * FROM student_details WHERE rollnum=?";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, roll);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                System.out.println("Roll Number : " + rs.getInt("rollnum"));
                System.out.println("Name        : " + rs.getString("sname"));
                System.out.println("College     : " + rs.getString("clgname"));
                System.out.println("City        : " + rs.getString("city"));
                System.out.println("Percentage  : " + rs.getDouble("percentage"));

                flag = true;

            } else {

                System.out.println("Student not found.");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
}