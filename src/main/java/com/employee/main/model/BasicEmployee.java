package com.employee.main.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class BasicEmployee {

    private String firstName;
    private char middleInitial;
    private String lastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfEmployment;

    public BasicEmployee() {}

    public BasicEmployee(BasicEmployee basicEmployee) {
        setFirstName(basicEmployee.getFirstName());
        setMiddleInitial(basicEmployee.getMiddleInitial());
        setLastName(basicEmployee.getLastName());
        setDateOfBirth(basicEmployee.getDateOfBirth());
        setDateOfEmployment(basicEmployee.getDateOfEmployment());
    }

    @ApiModelProperty(example = "Peter", required = true, value = "")
    @NotNull
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ApiModelProperty(example = "K", required = true, value = "")
    @NotNull
    public char getMiddleInitial() {
        return middleInitial;
    }
    public void setMiddleInitial(char middleInitial) {
        this.middleInitial = middleInitial;
    }

    @ApiModelProperty(example = "Nikolla", required = true, value = "")
    @NotNull
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ApiModelProperty(example = "1963-12-31", required = true, value = "")
    @NotNull
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @ApiModelProperty(example = "2018-12-01", required = true, value = "")
    @NotNull
    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }
    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }
}
