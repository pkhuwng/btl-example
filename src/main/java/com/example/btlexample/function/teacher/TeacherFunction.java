package com.example.btlexample.function.teacher;

import com.example.btlexample.objects.TeacherObject;
import com.example.btlexample.utils.ConnectionPool;

import java.util.ArrayList;

public interface TeacherFunction {
    boolean addTeacher(TeacherObject teacherObject);
    boolean removeTeacher(TeacherObject teacherObject);
    boolean editTeacher(TeacherObject teacherObject);

    TeacherObject getTeacher(int id);
    TeacherObject getTeacher(String name, String password);
    ArrayList<TeacherObject> getTeachers();
    ArrayList<TeacherObject> getSimilarTeachers(String keyword);

    ConnectionPool getConnectionPool();
    void releaseConnection();
}
