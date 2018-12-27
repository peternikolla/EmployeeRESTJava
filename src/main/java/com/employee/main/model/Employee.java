package com.employee.main.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Employee {

    public enum Status {ACTIVE, INACTIVE};

    private String id;
    private String firstName;
    private char middleInitial;
    private String lastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfEmployment;
    private Status status;

    @ApiModelProperty(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", required = false, value = "")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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

    @ApiModelProperty(example = "ACTIVE", required = false, value = "")
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
