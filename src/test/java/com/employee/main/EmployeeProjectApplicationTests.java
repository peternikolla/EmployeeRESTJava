package com.employee.main;

import java.util.List;

import com.employee.main.dao.EmployeeDAO;
import com.employee.main.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasSize;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeProjectApplicationTests {

    @Autowired
    private EmployeeDAO employeeRepository;

    @Test
    public void contextLoads() {
        // Test Employee
        Employee employee = employeeRepository.getEmployee("d290f1ee-6c54-4b01-90e6-d701748f0851");

        assert(employee != null);
        assert(employee.getFirstName().equals("Peter"));

        assertThat(employee.getFirstName(), equalTo("Peter"));

        // Test Get All Employees
        List<Employee> employees = employeeRepository.getAllEmployees();

        assertThat(employees, hasSize(3));
        assertThat(employees.get(0).getFirstName(), equalTo("Peter"));
        assertThat(employees.get(1).getLastName(), not(equalTo("Peter")));
    }

}

