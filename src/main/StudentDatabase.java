/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author tana_
 */
public class StudentDatabase {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String derbyClientDriver = "org.apache.derby.jdbc.ClientDriver";
        Class.forName(derbyClientDriver);
        String url = "jdbc:derby://localhost:1527/student";
        String user = "app";
        String passwd = "app";
        Connection con = DriverManager.getConnection(url, user, passwd);
        Statement stmt = con.createStatement();
        Student s1 = new Student(1, "AAAA", 3.25);
        Student s2 = new Student(2, "BBBB", 3.75);
        Student s3 = new Student(3, "CCCC", 2.0);
        Student s4 = new Student(4, "DDDD", 1.36);
        Student s5 = new Student(5, "EEEEE", 2.75);
        
        insertStudent(stmt, s1);
        insertStudent(stmt, s2);
        insertStudent(stmt, s3);
        insertStudent(stmt, s4);
        insertStudent(stmt, s5);
        ArrayList<Student> studentList = getAllStudent(con);
        printAllStudent(studentList);
        
        System.out.println("");
        
        s3.setName("PPPPPPP");
        updateStudentName(stmt, s3);
        studentList = getAllStudent(con);
        printAllStudent(studentList);
        
        System.out.println("");
        deleteStudent(stmt, s1);
        deleteStudent(stmt, s2);
        studentList = getAllStudent(con);
        printAllStudent(studentList);
        
        stmt.close();
        con.close();
    }
    
    public static void printAllStudent(ArrayList<Student> studentList) {
        for(Student s : studentList) {
           System.out.print(s.getId() + " ");
           System.out.print(s.getName() + " ");
           System.out.println(s.getGpa() + " ");
       }
    }
    
    public static ArrayList<Student> getAllStudent (Connection con) throws SQLException {
        String sql = "select * from student order by id";
        PreparedStatement ps = con.prepareStatement(sql);
        ArrayList<Student> studentList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
           Student student = new Student();
           student.setId(rs.getInt("id"));
           student.setName(rs.getString("name"));
           student.setGpa(rs.getDouble("gpa"));
           studentList.add(student);
       }
       rs.close();
       return studentList;
    }
    

    public static void insertStudent(Statement stmt, Student s) throws SQLException {
        String sql = "insert into student (id, name, gpa)"
                + " values (" + s.getId() + "," + "'" + s.getName() + "'" + "," + s.getGpa() + ")";
        int result = stmt.executeUpdate(sql);
        System.out.println("Insert " + result + " row");
    }

    public static void deleteStudent(Statement stmt, Student s) throws SQLException {
        String sql = "delete from student where id = " + s.getId();
        int result = stmt.executeUpdate(sql);
        //display result
        System.out.println("delete " + result + " row");
    }

    public static void updateStudentGpa(Statement stmt, Student s) throws SQLException {
        String sql = "update student set gpa  = " + s.getGpa()
                + " where id = " + s.getId();
        int result = stmt.executeUpdate(sql);
        //display result
        System.out.println("update " + result + " row");
    }

    public static void updateStudentName(Statement stmt, Student s) throws SQLException {
        String sql = "update student set name  = '" + s.getName() + "'"
                + " where id = " + s.getId();
        int result = stmt.executeUpdate(sql);
        //display result
        System.out.println("update " + result + " row");
    }

}
