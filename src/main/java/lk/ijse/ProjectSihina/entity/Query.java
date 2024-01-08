package lk.ijse.ProjectSihina.entity;


import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Query {
    String ClassID;
    String ClassName;
    String Stu_Count;
    LocalTime StartTime;
    LocalTime EndTime;
    String Subject;
    String Type;

    public Query(String classID, String className, String stu_Count) {
        ClassID = classID;
        ClassName = className;
        Stu_Count = stu_Count;
    }

    public Query(LocalTime startTime, LocalTime endTime, String className, String subject, String type) {
        StartTime = startTime;
        EndTime = endTime;
        ClassName = className;
        Subject = subject;
        Type = type;
    }
}
