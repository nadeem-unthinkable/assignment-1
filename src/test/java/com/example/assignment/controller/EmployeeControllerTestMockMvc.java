package com.example.assignment.controller;

import com.example.assignment.entity.Department;
import com.example.assignment.entity.Employee;
import com.example.assignment.repo.IEmployee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

//import static java.util.Collections.get;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class EmployeeControllerTestMockMvc {


    @Autowired
    private MockMvc mockMvc ;

    @MockBean
    private IEmployee iEmployee;



    Employee emp1 = new Employee();
    Employee emp2 = new Employee();


    Department dep1 = new Department();
    Department dep2 = new Department();

//    @BeforeAll
//    public void v1111() {
//        MockitoAnnotations.initMocks(this);
//    }

    @BeforeEach
    public void setUp(){
        emp1.setId(1);
        emp1.setSalary(20000);
        emp1.setEmail("jafarnadeem01@gmail.com");
        emp1.setAddress("New Delhi");
        emp1.setName("Nadeem Jafar");
        emp1.setPhone("8287562626");
        dep1.setId(101);
        dep1.setName("HR");
        dep1.setAddress("Gurgaon");
        emp1.setDepartmentName(dep1.getName());

        emp1.setDepartment(dep1);
    }


    @Test
    @Disabled
    public void testDemo() throws Exception {
//        mockMvc.perform(get("/employee/check"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Hello"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/employee/check")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @Disabled
    public void testAdd() throws Exception {
       when(iEmployee.save(emp1)).thenReturn(emp1);
       mockMvc.perform(MockMvcRequestBuilders.post("/employee/register").
               accept(MediaType.APPLICATION_JSON))
               .andDo(print()).
//               andExpect(status().isOk())
        andExpect( (ResultMatcher) jsonPath("$.['Body'].name").value("Nadeem"));

//               andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));


//               .andExpect(jsonPath("$"))
//       ;

    }

    @Test
    @Disabled
    public void createEmployeeAPI() throws Exception
    {
        when(iEmployee.save(emp1)).thenReturn(emp1);
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/employee/register")
                        .content(asJsonString(emp1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.['Body'].id").value("1"));
        Assertions.assertTrue(true);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



//    @Test
//    public void


}