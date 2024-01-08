package lk.ijse.ProjectSihina.entity;

import lombok.*;

import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Schedule {
    String Class_ID;
    String Sub_ID;
    String Teacher_ID;
    String Day;
    LocalTime StartTime;
    LocalTime EndTime;
}
