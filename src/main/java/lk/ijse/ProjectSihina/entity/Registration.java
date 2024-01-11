package lk.ijse.ProjectSihina.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Registration {
    String StuId;
    String payId;
    String StuName;
    String ClassId;
    double RegFee;
    LocalDate Date;
    LocalTime Time;
}
