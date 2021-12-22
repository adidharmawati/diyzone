package id.adidharmawati.diyzone.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tutorial {

    @PrimaryKey(autoGenerate = true)
    public int id_tutorial;

    public String judul;
    public String isi;

    public int getId_tutorial() {
        return id_tutorial;
    }

    public void setId_tutorial(int id_tutorial) {
        this.id_tutorial = id_tutorial;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
