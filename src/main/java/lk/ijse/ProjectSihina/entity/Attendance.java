package lk.ijse.ProjectSihina.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Attendance {
    String Att_id;
    String StuId;
    String ClassName;
    String SubName;
    String Month;
    LocalDate Date;
    LocalTime Time;
    String Type;
}
