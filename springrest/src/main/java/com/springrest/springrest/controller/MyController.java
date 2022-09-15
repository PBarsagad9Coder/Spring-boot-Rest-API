package com.springrest.springrest.controller;

import java.util.List;
import java.util.zip.ZipEntry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springrest.springrest.entity.Course;
import com.springrest.springrest.services.CourseService;

@RestController
public class MyController {
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/home")
public String home() {
	return "This is Home Page";
}
	@GetMapping("/courses")
	public List<Course> getCourses(){
	return this.courseService.getCourses();
	}
	@GetMapping("/courses/{courseid}")
	public Course getCourse(@PathVariable String courseid) {
		return this.courseService.getCourse(Long.parseLong(courseid));
	}
	
    @PostMapping(path="/courses",consumes="application/json")
	public Course addCourse(@RequestBody Course course) {
	return this.courseService.addCourse(course);
	}
    @PutMapping("/courses")
    public Course updateCourse(@RequestBody  Course course) {
    	return this.courseService.updateCourse(course);
    }
    @DeleteMapping("/courses/{courseid}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable  String courseid){
    	try {
			this.courseService.deleteCourse(Long.parseLong(courseid));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
