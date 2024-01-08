package lk.ijse.ProjectSihina.entity;

import javafx.scene.image.Image;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    String ID;
    String Name;
    String Email;
    String Address;
    LocalDate dob;
    String Gender;
    String contact;
    String Stu_Class;
    String Subject;
    Image StudentImage;
    String user;

    public Student(String ID, String name, String stu_Class, String email,String contact) {
        this.ID = ID;
        Name = name;
        Email = email;
        Stu_Class = stu_Class;
        this.contact = contact;
    }

    public Student( String name, String Subjects) {
        this.Name = name;
        this.Subject = Subjects;
    }
    public Student( String ID , String name, String Stu_Class) {
        this.ID = ID;
        this.Name = name;
        this.Stu_Class = Stu_Class;
    }

    public Student(String ID, String name, String email, String address, LocalDate dob, String gender, String contact,
                   String stu_Class, String subject, Image studentImage) {
        this.ID = ID;
        Name = name;
        Email = email;
        Address = address;
        this.dob = dob;
        Gender = gender;
        this.contact = contact;
        Stu_Class = stu_Class;
        Subject = subject;
        StudentImage = studentImage;
    }
}
