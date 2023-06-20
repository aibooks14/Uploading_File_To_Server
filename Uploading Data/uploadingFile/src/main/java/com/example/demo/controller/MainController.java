package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.helper.FileUploadHelper;

@RestController
public class MainController {
	
	@Autowired private FileUploadHelper fileUploadHelper ;

	@GetMapping("/")
	public ResponseEntity<String> getForm(){
		System.out.println("Testdone");
		return ResponseEntity.ok("working");
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){

//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getSize());
//		System.out.println(file.getContentType());
//		System.out.println(file.getName());
		
		try {
		
		if(file.isEmpty()) {
			System.out.println("*******************************1");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request Must Contain file");
		}
		
		
		
//		if(!file.getContentType().equals("image/jpeg")) {
//			System.out.println("*******************************2");
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only JPEG content allowed");
//		}
		
		boolean result = fileUploadHelper.uploadFile(file);
		if(result) {
			System.out.println("*******************************5");
			ResponseEntity.ok("Files is Uploaded successfully");
		}
		
		//file Upload code..
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some went wrong !!");
	}
}
