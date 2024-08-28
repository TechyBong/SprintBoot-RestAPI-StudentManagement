package com.springbot;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbot.entity.Students;
import com.springbot.repo.StudentSysRepo;

@RestController
public class StudentController {
	
	@Autowired
	StudentSysRepo stdRepo;
	
	@GetMapping(value = "/student")
	public List<Students> getStudent() {
		
		Iterable<Students> list=stdRepo.findAll();
		List<Students> std= new ArrayList<Students>();
		
		for (Students students : list) {
			
			std.add(students);
			System.out.println(">> \n\n"+students.getDisplay_name());
		}
		
		return std;
		
	}
	
	@GetMapping(value = "/student/{id}")
	public ResponseEntity<JSONObject> getStudentById(@PathVariable int id) throws ParseException {
		Students std=null;
		String json="";
		String json1=" \"status\": \"false\",\n \"msg\": \"No data Found\"";
		JSONObject jsonobj=null;
		try {
			std=stdRepo.findById(id).get();
			System.out.println(std);
			ObjectMapper objMapper=new ObjectMapper();
			json=objMapper.writeValueAsString(std);
			JSONParser parser = new JSONParser();  
			 jsonobj=(JSONObject) parser.parse(json);
			System.out.println(jsonobj);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("in Catch Block");
			
		}
		
		if(std==null) {
			JSONParser parser = new JSONParser();  
			JSONObject jsonobj1=(JSONObject) parser.parse("{"+json1+"}");
			System.out.println(jsonobj1);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonobj1);
		}
		
		System.out.println(std.getDisplay_name());
		return ResponseEntity.status(HttpStatus.OK).body(jsonobj);
		
	}
	
	@PostMapping(value = "/student")
	public ResponseEntity<Students> createStudent(@RequestBody Students std) {
		
		Students student=stdRepo.save(std);
		System.out.println(">>>>> \n\n\n"+new ResponseEntity<Students>(student, HttpStatus.CREATED));
		return new ResponseEntity<Students>(student, HttpStatus.CREATED);
		
	}
	/*
	 	@GetMapping(value = "/student")
	public String getStudentById(int id) {
		
		Students std=stdRepo.findById(id).get();
		
		System.out.println(std.getDisplay_name());
		return std.getDisplay_name();
		
	}
	 */
	
	@PutMapping(value = "/student/{id}")
	public ResponseEntity<Students> updateStudentById(@PathVariable int id,@RequestBody Students std) {
		
		Students student=stdRepo.save(std);
		
		return new ResponseEntity<Students>(student,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping(value = "/student/{id}")
	public ResponseEntity<Students> deleteStudentById(@PathVariable int id) {
		
		stdRepo.deleteById(id);
		
		return new ResponseEntity<Students>(HttpStatus.OK);
		
	}
	
}

