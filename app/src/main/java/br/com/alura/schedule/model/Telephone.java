package br.com.alura.schedule.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Student.class,
        parentColumns = "id",
        childColumns = "studentId",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE))
public class Telephone {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String number;
    private TelephoneType type;
    private int studentId;

    public Telephone(String number, TelephoneType type, int studentId) {
        this.number = number;
        this.type = type;
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TelephoneType getType() {
        return type;
    }

    public void setType(TelephoneType type) {
        this.type = type;
    }
}
