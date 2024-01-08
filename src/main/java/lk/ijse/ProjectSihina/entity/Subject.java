package lk.ijse.ProjectSihina.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Subject {
    String id;
    String subject;
    String AvailableClass;
    String teacherName;
    double MonthlyAmount;

    public Subject(String subject) {
        this.subject = subject;
    }
}
