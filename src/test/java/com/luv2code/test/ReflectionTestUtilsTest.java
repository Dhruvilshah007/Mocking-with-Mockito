package com.luv2code.test;


import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ReflectionTestUtilsTest {

    @Autowired
    ApplicationContext context;
    @Autowired
    CollegeStudent studentOne;
    @Autowired
    StudentGrades studentGrades;


    @BeforeEach
    public void studentBeforeEach(){
        studentOne.setFirstname("Eric");
        studentOne.setLastname("Roby");
        studentOne.setEmailAddress("eric.roby@luv2code_school.com");
        studentOne.setStudentGrades(studentGrades);


        //We are setting field directly for private fields of CollegeStudent class
        ReflectionTestUtils.setField(studentOne, "id", 1);
        ReflectionTestUtils.setField(studentOne, "studentGrades",
                new StudentGrades(new ArrayList<>(Arrays.asList(
                        100.0, 85.0, 76.50, 91.75))));

    }


    //Main use case is that we can access private fields and methods using refelctionUtils
    @Test
    public void getPrivateField(){

        //accessing private field directly
        assertEquals(1,ReflectionTestUtils.getField(studentOne,"id"));
    }


    //Calling private method using refelctionUtils
    @Test
    public void invokePrivateMethod() {
        assertEquals("Eric 1",
                ReflectionTestUtils.invokeMethod(studentOne, "getFirstNameAndId"),
                "Fail private method not call");
    }







}
