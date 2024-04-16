package com.mrthinkj.integratemiddlewareapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.annotation.Documented;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("employees")
public class MongoEmployee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int vacationDays;
    private int paidToDate;
    private int paidLastYear;
    private int payRate;
    private int payRateId;
    private Date createdAt;
    private Date updatedAt;

}
