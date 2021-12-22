package id.adidharmawati.diyzone.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.adidharmawati.diyzone.database.entity.Tutorial;
import id.adidharmawati.diyzone.database.entity.User;

@Dao
public interface TutorialDao {
    @Query("SELECT * FROM tutorial ORDER BY id_tutorial DESC")
    List<Tutorial> getAll();

    @Query("INSERT INTO tutorial(judul,isi)VALUES(:judul,:isi)")
    void insertAll(String judul, String isi);

    @Query("SELECT * FROM tutorial WHERE id_tutorial=:id")
    List<Tutorial> selectTutor(int id);

    @Query("DELETE FROM tutorial WHERE id_tutorial=:id")
    void delete(int id);

    @Query("UPDATE tutorial SET judul= (:judul), isi= (:isi) WHERE id_tutorial= (:id)")
    void updateTutor(int id, String judul, String isi);
}
