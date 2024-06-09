package com.example.btlexample.function.teacher;

import com.example.btlexample.objects.TeacherObject;
import com.example.btlexample.utils.ConnectionPool;
import com.example.btlexample.utils.ConnectionPoolImpl;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherFunctionImpl implements TeacherFunction{
    private Connection connection;
    private final ConnectionPool connectionPool;
    public TeacherFunctionImpl(ConnectionPool connectionPool) {
        if (connectionPool == null) {
            this.connectionPool = new ConnectionPoolImpl();
        } else {
            this.connectionPool = connectionPool;
        }
        try {
            this.connection= this.connectionPool.getConnection("Teacher");
            if (this.connection.getAutoCommit()) this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            printSQLException(e);
        }

    }

    private boolean execute(PreparedStatement preparedStatement){
        if (preparedStatement == null) return false;
        //preparedStatement được biên dịch và truyền đầy đủ tham số
        try {
            int result = preparedStatement.executeUpdate();
            if (result==0) {
                this.connection.rollback();
                return false;
            }
            //xác nhận thực thi sau cùng
            this.connection.commit();
            //đóng kết nối
            this.releaseConnection();
            return true;
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                printSQLException(ex);
            }
        } finally {
            preparedStatement = null;
        }
        return false;
    }

    private boolean isExist(TeacherObject item){
        String sql = "select teacher_id from teacher where teacher_username=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, item.getTeacher_username());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet!=null && resultSet.next()) return true;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    @Override
    public boolean addTeacher(TeacherObject teacherObject) {
        if (isExist(teacherObject)) return false;
        String sql = "INSERT INTO teacher (teacher_username, teacher_password, teacher_fullname, teacher_email, teacher_phone, teacher_address, teacher_gender, teacher_age, teacher_major, teacher_birthday, teacher_degree, teacher_class) VALUES (?,md5(?),?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, teacherObject.getTeacher_username());
            preparedStatement.setString(2, teacherObject.getTeacher_password());
            preparedStatement.setString(3, teacherObject.getTeacher_fullname());
            preparedStatement.setString(4, teacherObject.getTeacher_email());
            preparedStatement.setString(5, teacherObject.getTeacher_phone());
            preparedStatement.setString(6, teacherObject.getTeacher_address());
            preparedStatement.setString(7, teacherObject.getTeacher_gender());
            preparedStatement.setInt(8, teacherObject.getTeacher_age());
            preparedStatement.setString(9, teacherObject.getTeacher_major());
            preparedStatement.setString(10, teacherObject.getTeacher_birthday());
            preparedStatement.setString(11, teacherObject.getTeacher_degree());
            preparedStatement.setString(12, teacherObject.getTeacher_class());
            this.releaseConnection();
            return this.execute(preparedStatement);
        } catch (SQLException e) {
            try {
                printSQLException(e);
                this.connection.rollback();
            } catch (SQLException ex) {
                printSQLException(ex);
            }
        }
        return false;
    }

    @Override
    public boolean removeTeacher(TeacherObject teacherObject) {
        if (!isExist(teacherObject)) return false;
        String sql = "DELETE FROM teacher WHERE teacher_username=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, teacherObject.getTeacher_username());
            return this.execute(preparedStatement);
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    @Override
    public boolean editTeacher(TeacherObject teacherObject) {
        /*StringBuilder sql = new StringBuilder();
        sql.append("update teacher").append("set teacher_pass=md5(?), teacher_fullname=?, teacher_email=?, teacher_phone=?, teacher_address=?, teacher_gender=?, teacher_age=?, teacher_major=?, teacher_birthday=?, teacher_degree=?, teacher_class=?").append(" where teacher_id=?");*/
        String sql = "update teacher set teacher_password=md5(?), teacher_fullname=?, teacher_email=?, teacher_phone=?, teacher_address=?, teacher_gender=?, teacher_age=?, teacher_major=?, teacher_birthday=?, teacher_degree=?, teacher_class=? where teacher_username=? or teacher_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, teacherObject.getTeacher_password());
            preparedStatement.setString(2, teacherObject.getTeacher_fullname());
            preparedStatement.setString(3, teacherObject.getTeacher_email());
            preparedStatement.setString(4, teacherObject.getTeacher_phone());
            preparedStatement.setString(5, teacherObject.getTeacher_address());
            preparedStatement.setString(6, teacherObject.getTeacher_gender());
            preparedStatement.setInt(7, teacherObject.getTeacher_age());
            preparedStatement.setString(8, teacherObject.getTeacher_major());
            preparedStatement.setString(9, teacherObject.getTeacher_birthday());
            preparedStatement.setString(10, teacherObject.getTeacher_degree());
            preparedStatement.setString(11, teacherObject.getTeacher_class());
            preparedStatement.setString(12, teacherObject.getTeacher_username());
            preparedStatement.setInt(13,teacherObject.getTeacher_id());
            return this.execute(preparedStatement);
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                printSQLException(ex);
            }
        }
        return false;
    }

    @Override
    public TeacherObject getTeacher(int id) {
        String sql = "select * from teacher where teacher_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet!=null && resultSet.next()) {
                TeacherObject teacherObject = new TeacherObject();
                teacherObject.setTeacher_id(id);
                return getTeacherObject(resultSet, teacherObject);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    @Override
    public TeacherObject getTeacher(String name, String password) {
        String sql = "select * from teacher where teacher_username=? and teacher_password=md5(?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet!=null && resultSet.next()) {
                TeacherObject teacherObject = new TeacherObject();
                teacherObject.setTeacher_id(resultSet.getInt("teacher_id"));
                this.releaseConnection();
                return getTeacherObject(resultSet, teacherObject);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    @Override
    public ArrayList<TeacherObject> getTeachers() {
        ArrayList<TeacherObject> teacherObjects = new ArrayList<>();
        String sql = "select * from teacher order by teacher_id desc";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet!=null && resultSet.next()) {
                TeacherObject teacherObject = new TeacherObject();
                teacherObject.setTeacher_id(resultSet.getInt("teacher_id"));
                teacherObjects.add(getTeacherObject(resultSet, teacherObject));
            }
            this.releaseConnection();
            return teacherObjects;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    @Override
    public ArrayList<TeacherObject> getSimilarTeachers(String keyword) {
        ArrayList<TeacherObject> teacherObjects = new ArrayList<>();
        String sql = "select * from teacher where teacher_fullname like concat(\"%\", ?, \"%\") or teacher_username like concat(\"%\", ?, \"%\")";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1,keyword);
            preparedStatement.setString(2,keyword);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet!=null && resultSet.next()) {
                TeacherObject teacherObject = new TeacherObject();
                teacherObject.setTeacher_id(resultSet.getInt("teacher_id"));
                teacherObjects.add(getTeacherObject(resultSet, teacherObject));
            }
            this.releaseConnection();
            return teacherObjects;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return teacherObjects;
    }

    @Override
    public ConnectionPool getConnectionPool() {
        return this.connectionPool;
    }

    @Override
    public void releaseConnection() {
        try {
            this.connectionPool.releaseConnection(connection,"Teacher");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @NotNull
    private TeacherObject getTeacherObject(ResultSet resultSet, TeacherObject teacherObject) throws SQLException {
        teacherObject.setTeacher_username(resultSet.getString("teacher_username"));
        teacherObject.setTeacher_password(resultSet.getString("teacher_password"));
        teacherObject.setTeacher_fullname(resultSet.getString("teacher_fullname"));
        teacherObject.setTeacher_email(resultSet.getString("teacher_email"));
        teacherObject.setTeacher_phone(resultSet.getString("teacher_phone"));
        teacherObject.setTeacher_address(resultSet.getString("teacher_address"));
        teacherObject.setTeacher_gender(resultSet.getString("teacher_gender"));
        teacherObject.setTeacher_age(resultSet.getInt("teacher_age"));
        teacherObject.setTeacher_major(resultSet.getString("teacher_major"));
        teacherObject.setTeacher_birthday(resultSet.getString("teacher_birthday"));
        teacherObject.setTeacher_degree(resultSet.getString("teacher_degree"));
        teacherObject.setTeacher_class(resultSet.getString("teacher_class"));
        return teacherObject;
    }
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
