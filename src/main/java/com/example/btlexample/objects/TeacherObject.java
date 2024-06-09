package com.example.btlexample.objects;

import java.util.Objects;

public class TeacherObject {
    private int teacher_id;
    private String teacher_username;
    private String teacher_password;
    private String teacher_fullname;
    private String teacher_email;
    private String teacher_phone;
    private String teacher_address;
    private String teacher_gender;
    private int teacher_age;
    private String teacher_major;
    private String teacher_birthday;
    private String teacher_degree;
    private String teacher_class;

    public TeacherObject() {
    }

    public TeacherObject(TeacherObject teacherObject) {
        this.teacher_id = teacherObject.getTeacher_id();
        this.teacher_username = teacherObject.getTeacher_username();
        this.teacher_password = teacherObject.getTeacher_password();
        this.teacher_fullname = teacherObject.getTeacher_fullname();
        this.teacher_email = teacherObject.getTeacher_email();
        this.teacher_phone = teacherObject.getTeacher_phone();
        this.teacher_address = teacherObject.getTeacher_address();
        this.teacher_gender = teacherObject.getTeacher_gender();
        this.teacher_age = teacherObject.getTeacher_age();
        this.teacher_major = teacherObject.getTeacher_major();
        this.teacher_birthday = teacherObject.getTeacher_birthday();
        this.teacher_degree = teacherObject.getTeacher_degree();
        this.teacher_class = teacherObject.getTeacher_class();
    }

    public TeacherObject(int teacher_id, String teacher_username, String teacher_password, String teacher_fullname, String teacher_email, String teacher_phone, String teacher_address, String teacher_gender, int teacher_age, String teacher_major, String teacher_birthday, String teacher_degree, String teacher_class) {
        this.teacher_id = teacher_id;
        this.teacher_username = teacher_username;
        this.teacher_password = teacher_password;
        this.teacher_fullname = teacher_fullname;
        this.teacher_email = teacher_email;
        this.teacher_phone = teacher_phone;
        this.teacher_address = teacher_address;
        this.teacher_gender = teacher_gender;
        this.teacher_age = teacher_age;
        this.teacher_major = teacher_major;
        this.teacher_birthday = teacher_birthday;
        this.teacher_degree = teacher_degree;
        this.teacher_class = teacher_class;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public String getTeacher_username() {
        return teacher_username;
    }

    public TeacherObject setTeacher_username(String teacher_username) {
        this.teacher_username = teacher_username;
        return this;
    }

    public String getTeacher_password() {
        return teacher_password;
    }

    public TeacherObject setTeacher_password(String teacher_password) {
        this.teacher_password = teacher_password;
        return this;
    }

    public TeacherObject setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
        return this;
    }

    public String getTeacher_fullname() {
        return teacher_fullname;
    }

    public TeacherObject setTeacher_fullname(String teacher_fullname) {
        this.teacher_fullname = teacher_fullname;
        return this;
    }

    public String getTeacher_email() {
        return teacher_email;
    }

    public TeacherObject setTeacher_email(String teacher_email) {
        this.teacher_email = teacher_email;
        return this;
    }

    public String getTeacher_phone() {
        return teacher_phone;
    }

    public TeacherObject setTeacher_phone(String teacher_phone) {
        this.teacher_phone = teacher_phone;
        return this;
    }

    public String getTeacher_address() {
        return teacher_address;
    }

    public TeacherObject setTeacher_address(String teacher_address) {
        this.teacher_address = teacher_address;
        return this;
    }

    public String getTeacher_gender() {
        return teacher_gender;
    }

    public TeacherObject setTeacher_gender(String teacher_gender) {
        this.teacher_gender = teacher_gender;
        return this;
    }

    public int getTeacher_age() {
        return teacher_age;
    }

    public TeacherObject setTeacher_age(int teacher_age) {
        this.teacher_age = teacher_age;
        return this;
    }

    public String getTeacher_major() {
        return teacher_major;
    }

    public TeacherObject setTeacher_major(String teacher_major) {
        this.teacher_major = teacher_major;
        return this;
    }

    public String getTeacher_birthday() {
        return teacher_birthday;
    }

    public TeacherObject setTeacher_birthday(String teacher_birthday) {
        this.teacher_birthday = teacher_birthday;
        return this;
    }

    public String getTeacher_degree() {
        return teacher_degree;
    }

    public TeacherObject setTeacher_degree(String teacher_degree) {
        this.teacher_degree = teacher_degree;
        return this;
    }

    public String getTeacher_class() {
        return teacher_class;
    }

    public TeacherObject setTeacher_class(String teacher_class) {
        this.teacher_class = teacher_class;
        return this;
    }

    @Override
    public String toString() {
        return "TeacherObject{" +
                "teacher_id=" + teacher_id +
                ", teacher_username='" + teacher_username + '\'' +
                ", teacher_password='" + teacher_password + '\'' +
                ", teacher_fullname='" + teacher_fullname + '\'' +
                ", teacher_email='" + teacher_email + '\'' +
                ", teacher_phone='" + teacher_phone + '\'' +
                ", teacher_address='" + teacher_address + '\'' +
                ", teacher_gender='" + teacher_gender + '\'' +
                ", teacher_age=" + teacher_age +
                ", teacher_major='" + teacher_major + '\'' +
                ", teacher_birthday='" + teacher_birthday + '\'' +
                ", teacher_degree='" + teacher_degree + '\'' +
                ", teacher_class='" + teacher_class + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherObject that = (TeacherObject) o;
        return getTeacher_id() == that.getTeacher_id() && getTeacher_age() == that.getTeacher_age() && Objects.equals(getTeacher_username(), that.getTeacher_username()) && Objects.equals(getTeacher_password(), that.getTeacher_password()) && Objects.equals(getTeacher_fullname(), that.getTeacher_fullname()) && Objects.equals(getTeacher_email(), that.getTeacher_email()) && Objects.equals(getTeacher_phone(), that.getTeacher_phone()) && Objects.equals(getTeacher_address(), that.getTeacher_address()) && Objects.equals(getTeacher_gender(), that.getTeacher_gender()) && Objects.equals(getTeacher_major(), that.getTeacher_major()) && Objects.equals(getTeacher_birthday(), that.getTeacher_birthday()) && Objects.equals(getTeacher_degree(), that.getTeacher_degree()) && Objects.equals(getTeacher_class(), that.getTeacher_class());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeacher_id(), getTeacher_username(), getTeacher_password(), getTeacher_fullname(), getTeacher_email(), getTeacher_phone(), getTeacher_address(), getTeacher_gender(), getTeacher_age(), getTeacher_major(), getTeacher_birthday(), getTeacher_degree(), getTeacher_class());
    }
}