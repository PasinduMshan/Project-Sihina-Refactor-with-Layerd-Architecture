package lk.ijse.ProjectSihina.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Exam {
    String ExamId;
    LocalDate date;
    LocalTime StartTime;
    LocalTime EndTime;
    String Description;
    String ClassID;
    String SubjectID;
}
