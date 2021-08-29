//package com.woodapp.auth;
//
//import javax.validation.Valid;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.woodapp.users.RegisteredUsers;
//
//
//@Controller
//public class ValidatingController implements WebMvcConfigurer {
//
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/results").setViewName("results");
//	}
//
//	@GetMapping("/")
//	public String showForm(RegisteredUsers registeredUsers) {
//		return "form";
//	}
//
//	@PostMapping("/")
//	public String checkPersonInfo(@Valid RegisteredUsers registeredUsers, BindingResult bindingResult) {
//
//		if (bindingResult.hasErrors()) {
//			return "form";
//		}
//
//		return "redirect:/results";
//	}
//}
