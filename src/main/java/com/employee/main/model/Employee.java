package com.employee.main.model;

import io.swagger.annotations.ApiModelProperty;

public class Employee extends BasicEmployee {

    public enum Status {ACTIVE, INACTIVE};

    //@ApiModelProperty(hidden = true)
    private String id;
    private Status status;

    public Employee() {}

    public Employee(BasicEmployee basicEmployee) {
        super(basicEmployee);
    }

    @ApiModelProperty(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", required = false, value = "")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(example = "ACTIVE", required = false, value = "")
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
