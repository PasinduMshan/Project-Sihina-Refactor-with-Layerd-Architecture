package lk.ijse.ProjectSihina.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Payment {
    String PayID;
    String StuID;
    String type;
    String StuClass;
    String Subject;
    String PayMonth;
    LocalDate date;
    LocalTime time;
    double Amount;

    public Payment(String payID, String subject, double amount) {
        PayID = payID;
        Subject = subject;
        Amount = amount;
    }

    public Payment(String payID, String stuClass, String subject, String payMonth, double amount) {
        PayID = payID;
        StuClass = stuClass;
        Subject = subject;
        PayMonth = payMonth;
        Amount = amount;
    }

    public Payment(String payID, String stuID, String stuClass, LocalDate date, double amount) {
        PayID = payID;
        StuID = stuID;
        StuClass = stuClass;
        this.date = date;
        Amount = amount;
    }
}
