package id.adidharmawati.diyzone.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

import id.adidharmawati.diyzone.database.entity.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("INSERT INTO User (username,email,password,gender,age) VALUES(:username,:email,:password,:gender,:age)")
    void insertUser(String username, String email, String password, String gender, String age);

    @Query("SELECT * FROM user WHERE username = (:username) AND password = (:password)")
    List<User> findUser(String username, String password);

    @Query("SELECT * FROM user WHERE id_user= (:id)")
    List<User> selectUser(int id);

    @Delete
    void delete(User users);
}
