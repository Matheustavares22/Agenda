package br.com.alura.schedule.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class Student implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String name;
    private String email;

    public Student() {
    }

    public boolean hasValidId() {
        return id > 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public String toString() {
        return this.name;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
    }

    protected Student(Parcel in) {
        id = in.readInt();
        name = in.readString();
        email = in.readString();
    }

}
