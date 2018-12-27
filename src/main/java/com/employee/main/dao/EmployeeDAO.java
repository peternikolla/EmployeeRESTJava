package com.employee.main.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.employee.main.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {

    ArrayList<Employee> employees = new ArrayList<Employee>();

    /**
     * Employee Repository
     * Loads data from file in resources folder, file name is employee.json
     */
    EmployeeDAO() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Employee>> typeReference = new TypeReference<List<Employee>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/data/employees.json");
        try {
            employees = mapper.readValue(inputStream,typeReference);
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Get Employee
     * @param id
     * @return
     */
    public Employee getEmployee(String id) {
        for(Employee employee : employees) {
            if (employee.getId().equals(id) ) {
                if ( employee.getStatus() == Employee.Status.INACTIVE ) {
                    throw new NoSuchElementException();
                }
                return employee;
            }
        }

        throw new NoSuchElementException();
    }

    /**
     *
     * @param newEmployee
     * @return
     */
    public Employee addEmployee(Employee newEmployee) {
        newEmployee.setId(getUniqueId());
        newEmployee.setStatus(Employee.Status.ACTIVE);
        employees.add(newEmployee);
        return newEmployee;
    }

    /**
     *
     * @param id
     * @param updatedEmployee
     * @return
     */
    public Employee updateEmployee(String id, Employee updatedEmployee) {
        for(Employee employee : employees) {
            if (employee.getId().equals(id) ) {
                String firstName = updatedEmployee.getFirstName();
                String lastName = updatedEmployee.getLastName();
                Date dateOfBirth = updatedEmployee.getDateOfBirth();
                Date dateOfEmployment = updatedEmployee.getDateOfEmployment();

                if (firstName != null && firstName.length() > 0) employee.setFirstName(firstName);
                if (updatedEmployee.getMiddleInitial() != 0 ) employee.setMiddleInitial(updatedEmployee.getMiddleInitial());
                if (lastName != null && lastName.length() > 0) employee.setLastName(lastName);
                if (dateOfBirth != null ) employee.setDateOfBirth(dateOfBirth);
                if (dateOfEmployment != null ) employee.setDateOfEmployment(dateOfEmployment);
                return employee;
            }
        }

        throw new NoSuchElementException();
    }

    /**
     * Delete Employee
     * @param id
     */
    public void deleteEmployee(String id) {
        for(Employee employee : employees) {
            if (employee.getId().equals(id) ) {
                employee.setStatus(Employee.Status.INACTIVE);
                return;
            }
        }

        throw new NoSuchElementException();
    }

    public List<Employee> getAllEmployees() {
        ArrayList<Employee> activeEmployees = new ArrayList<Employee>();

        for(Employee employee : employees) {
            if ( employee.getStatus() == Employee.Status.ACTIVE ) {
                activeEmployees.add(employee);
            }
        }

        return activeEmployees;
    }

    private String getUniqueId() {
        boolean unique;
        UUID uuid;

        do {
            unique = true;
            uuid = UUID.randomUUID();
            for(Employee employee : employees) {
                if (employee.getId().equals(uuid) ) {
                    unique = false;
                    continue;
                }
            }
        } while( !unique );

        return uuid.toString();
    }
}
