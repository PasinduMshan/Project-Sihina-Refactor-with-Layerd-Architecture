package lk.ijse.ProjectSihina.entity;

import javafx.scene.image.Image;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Teacher {
    String Id;
    String Name;
    String Address;
    String Email;
    String Subjects;
    String ContactNo;
    Image imageTeacher;

    public Teacher(String name) {
        Name = name;
    }
}
