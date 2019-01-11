package com.employee.main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.employee.main.model.Employee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;

import java.util.Calendar;
import java.util.GregorianCalendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeProjectControllerTests extends AbstractControllerTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getEmployee() throws Exception {
        String uri = "/api/v1/employee/d290f1ee-6c54-4b01-90e6-d701748f0851";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Employee employee = super.mapFromJson(content, Employee.class);
        assertThat(employee.getFirstName(), equalTo("Peter"));
    }

    @Test
    public void getEmployeeList() throws Exception {
        String uri = "/api/v1/employees/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Employee[] productlist = super.mapFromJson(content, Employee[].class);
        assertTrue(productlist.length > 0);
    }

    @Test
    public void addEmployee() throws Exception {
        String uri = "/api/v1/employee/";

        Employee employee = new Employee();
        employee.setFirstName("Test");
        employee.setLastName("Tester");
        employee.setMiddleInitial('T');

        GregorianCalendar calendar = new GregorianCalendar();

        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.YEAR, 2000);
        employee.setDateOfBirth(calendar.getTime());

        calendar.set(Calendar.YEAR, 2019);
        employee.setDateOfEmployment(calendar.getTime());

        String inputJson = super.mapToJson(employee);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        Employee retemployee = super.mapFromJson(content, Employee.class);
        assertThat(retemployee.getFirstName(), equalTo("Test"));
    }

    @Test
    public void updateEmployee() throws Exception {
        String uri = "/api/v1/employee/d290f1ee-6c54-4b01-90e6-d701748f0851";
        Employee employee = new Employee();
        employee.setFirstName("Test");
        String inputJson = super.mapToJson(employee);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        Employee retemployee = super.mapFromJson(content, Employee.class);
        assertThat(retemployee.getFirstName(), equalTo("Test"));
    }

    @Test
    public void deleteEmployee() throws Exception {
        String uri = "/api/v1/employee/d290f1ee-6c54-4b01-90e6-d701748f0851";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .header("api_key", new String("ABC"))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        Employee retemployee = super.mapFromJson(content, Employee.class);
        assertThat(retemployee.getStatus(), equalTo(Employee.Status.ACTIVE));
    }
}