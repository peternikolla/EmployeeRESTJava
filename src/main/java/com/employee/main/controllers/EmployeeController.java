package com.employee.main.controllers;

import com.employee.main.dao.EmployeeDAO;
import com.employee.main.model.BasicEmployee;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.employee.main.model.Employee;

import java.util.List;
import java.util.NoSuchElementException;
import javax.validation.Valid;

@RestController
@Api(value="employee", description="Employee API's", produces ="application/json")
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeRepository;

    /**
     * Get Employee
     * @param id
     * @return
     */
    @ApiOperation(value="Get Active Employee",response= Employee.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Success",response= Employee.class),
            @ApiResponse(code=404,message="Employee not found")
    })
    @RequestMapping(value="/employee/{id}",
            method=RequestMethod.GET,
            produces="application/json"
    )
    public ResponseEntity<Employee> getEmployee(@ApiParam(value = "Id of employee to find", required=true) @PathVariable("id") String id) {

        try {
            Employee employee = employeeRepository.getEmployee(id);
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add Employee
     * @param newEmployee
     * @return
     */
    @ApiOperation(value="Add Employee",response= Employee.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Added")
    })
    @RequestMapping(value="/employee",
            method=RequestMethod.POST,
            produces="application/json"
    )
    public ResponseEntity<Employee> AddEmployee(@Valid @RequestBody BasicEmployee newEmployee) {
        Employee retEmployee = employeeRepository.addEmployee(newEmployee);
        return new ResponseEntity<Employee>(retEmployee, HttpStatus.OK);
    }

    /**
     * Update Employee
     * @param id
     * @param updatedEmployee
     * @return
     */
    @ApiOperation(value="Update Employee",response=Employee.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Updated"),
            @ApiResponse(code=404,message="Employee not found")
    })
    @RequestMapping(value="/employee/{id}",
            method=RequestMethod.PUT,
            produces="application/json"
    )
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody BasicEmployee updatedEmployee) {
        try {
            Employee retEmployee = employeeRepository.updateEmployee(id, updatedEmployee);
            return new ResponseEntity<Employee>(retEmployee, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Delete Employee
     * @param id
     * @return
     */
    @ApiOperation(value="Make Employee InActive",response=Employee.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Ok"),
            @ApiResponse(code=400,message="Invalid API key"),
            @ApiResponse(code=404,message="Employee not found")
    })
    @RequestMapping(value="/employee/{id}",
            method=RequestMethod.DELETE
    )
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id, @RequestHeader(value="api_key", required=true) String apiKey) {

        if ( !apiKey.equals("ABC") ) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        try {
            employeeRepository.deleteEmployee(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * List all active employees
     * @return
     */
    @ApiOperation(value="List Active Employees",response= Employee.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Success",response= Employee.class),
    })
    @RequestMapping(value="/employees",
            method=RequestMethod.GET,
            produces="application/json"
    )
    public List<Employee> listEmployee() {

        List<Employee> employees = employeeRepository.getAllEmployees();

        return employees;
    }
}
