package com.example.connectdb1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserRespository userRespository;
	
	@GetMapping("/users")
	public List<Users>GetAllUsers(){
		var result = userRespository.findAll();
		return result;
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> AddUsers (@RequestBody Users user){
		
		if(user.getEmail() == null || user.getEmail() == "") {
			return ResponseEntity.status(405).body("can not add empty email");
		}
		else {
			userRespository.save(user);
			return ResponseEntity.status(200).body(user);
		}
	}
}
