package com.mrthinkj.integratemiddlewareapplication.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MergePerson {
    private String id;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private int zip;
    private String email;
    private String phoneNumber;
    private String socialSecurityNumber;
    private String driversLicense;
    private String maritalStatus;
    private boolean gender;
    private boolean shareholderStatus;
    private Integer benefitPlans;
    private String ethnicity;
    public int vacationDays;
    public int paidToDate;
    public int paidLastYear;
    public int payRate;
    public int payRateId;
    public Date createdAt;
    public Date updatedAt;
}
