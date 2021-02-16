package com.springboot.thymeleaf.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.thymeleaf.thymeleafdemo.entity.Employee;
import com.springboot.thymeleaf.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService)
	{
		this.employeeService=employeeService;
	}
		
		
		@GetMapping("/list")
		public String listEmployees( Model model)
		{
			
		List <Employee> employees =	employeeService.findAll();
		
		model.addAttribute("employees", employees);
		
		return "employees/list-employees";
}
		
		
		@GetMapping("/showFormForAdd")
			public String show(Model model)
			{
				Employee emp = new Employee();
				model.addAttribute("employee", emp);
				return "employees/employee-form";
			}

	@PostMapping("/save")
	public String save(@ModelAttribute("employee") Employee emoloyee)
	{
	
		employeeService.save(emoloyee);
		
		return "redirect:/employees/list";
	}
	
	@GetMapping("/showFormForUpdate")
	
	public String showFormForUpdate(@RequestParam ("employeeId") int id, Model model)
	{
		Employee emp = employeeService.findById(id);
		
		model.addAttribute("employee", emp);
		
		return "employees/employee-form";
		
	}
	
@GetMapping("/delete")
	
	public String delete(@RequestParam ("employeeId") int id)
	{
		employeeService.deleteById(id);
		return "redirect:/employees/list";
		
	}
	
	
}