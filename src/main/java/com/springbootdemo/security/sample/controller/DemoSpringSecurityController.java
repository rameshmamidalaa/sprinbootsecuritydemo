/**
 * 
 */
package com.springbootdemo.security.sample.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Ramesh_Mamidala
 *
 * This <code>DemoSpringSecurityController</code> is used to demo the Spring security feature.
 */
@RestController
@Slf4j
public class DemoSpringSecurityController {
	
	/**
	 * @return user 
	 */
	@RequestMapping("/user")
	public String getUserPage() {
		log.info("I am in getUserPage()::::");
		return "Welcome to the USER";
	}
	/**
	 * @return admin
	 */
	@RequestMapping("/admin")
	public String getAdminPage() {
		log.info("I am in getAdminPage():::::");
		return "Welcome to the ADMIN";
	}
	
	/**
	 * @return home
	 */
	@RequestMapping("/home")
	public String getHomePage() {
		log.info("I am in getHomePage():::::");
		return "Welcome to the HOME";
	}
	
	/**
	 * @return info
	 */
	@RequestMapping("/info")
	public String getInfoPage() {
		log.info("I am in getInfoPage():::::");
		return "Note: Explaining about the Springboot Security Demo";
	}

}
