package com.springbot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springbot.entity.Students;
import com.springbot.repo.StudentSysRepo;

@SpringBootTest
class StudentManagementSystemApplicationTests {
	
	@Autowired
	StudentSysRepo repo;

	@Test
	void contextLoads() {
		System.out.println("Print ");
		StudentController obj=new StudentController();
		//obj.getStudentById(1);
		Students students=repo.findById(1).get();
		System.out.println(students.getDob());
	}

}
