package com.issuetracker.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
	
	@Autowired
	private IssuesRepository issueRepo;
	
	@Autowired
	private EquipmentRepository equipmentRepo;
	
	@Autowired
	public IssueService issueService;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public EquipmentService eqService;
	
	@GetMapping("")
	public String viewHomepage(){
		return "index";
	}
	
	@GetMapping("/admin_about")
	public String viewAboutPage(){
		return "admin_about";
	}
	
	@GetMapping("/sign_up")
	public String viewSignUp(Model model) {
		model.addAttribute("user", new User());
		
		return "sign_up";
	}
	
	@GetMapping("/sign_in")
	public String viewSignIn(Model model) {
		model.addAttribute("user", new User());
		
		return "redirect:/dashboard";
	}
	
	@PostMapping("/process_sign_up")
	public String processSignUp(User user) {

		userService.saveUserWithDefaultRole(user);
		
		return "sign_up_successful";
	}
	
	@GetMapping("/dashboard")
	public String viewDashboard(Model model) {
		List<Issues> issueList = issueService.listAll();
		model.addAttribute("issueList", issueList);
		
		return "dashboard";
	}
	
	@PostMapping("/process_add_issue")
	public String addNewIssue(Issues issue) {
		issueRepo.save(issue);
		
		return "redirect:/dashboard";
	}
	
	@PostMapping("/process_add_equipment")
	public String addNewEquipment(Equipment equip) {
		eqService.save(equip);
		
		return "redirect:/admin_dashboard";
	}
	
	@GetMapping("/submit_issues")
	public String viewIssuesPage(Model model) {
		model.addAttribute("issue", new Issues());
		
		return "submit_issues";
	}
	
	@GetMapping("/add_equipment")
	public String addNewEquipment(Model model) {
		model.addAttribute("equipmentModel", new Equipment());
		
		return "add_equipment";
	}
	
	@GetMapping("/user_about")
	public String viewUserAboutPage() {
		return "user_about";
	}
	
	@GetMapping("/view_equipment")
	public String viewEquipmentPage(Model model) {
		List<Equipment> eqList = equipmentRepo.findAll();
		model.addAttribute("listEq", eqList);
		
		return "view_equipment";
	}
	
	@GetMapping("/admin_dashboard")
	public String viewAdminDashboard(Model model) {

		List<Issues> issueList = issueService.listAll();
		model.addAttribute("issueList", issueList);
		
		return "admin_dashboard";
	}
	
	@GetMapping("/user_list")
	public String viewUsersList(Model model) {
		List<User> userList = userService.listAll();
		
		model.addAttribute("userlist", userList);
		
		
		return "user_list";
	}
	
	@GetMapping("/issues/edit/{id}")
	public String editIssue(@PathVariable("id") Long id, Model model) {
		Issues issue = issueService.get(id);
		model.addAttribute("editIssue", issue);
		return "edit_issue";
	}
	
	@GetMapping("/equipment/edit/{id}")
	public String editEquipment(@PathVariable("id") Long id, Model model) {
		Equipment equip = eqService.get(id);
		model.addAttribute("editEquipment", equip);
		return "edit_equipment";
	}
	
	@GetMapping("/user/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = userService.get(id);
		List<Role> listRoles = userService.getRoles();
		
		model.addAttribute("editUser", user);
		model.addAttribute("listRoles", listRoles);
		
		return "edit_user";
	}
	
	@PostMapping("/user/save")
	public String saveEditedUser(User user) {
		userService.save(user);
		return "redirect:/user_list";
	}
	
	@PostMapping("/issue/save")
	public String saveEditedIssue(Issues issue) {
		issueService.save(issue);
		return "redirect:/admin_dashboard";
	}
	
	@PostMapping("/equipment/save")
	public String saveEditedEquipment(Equipment equip) {
		eqService.save(equip);
		return "redirect:/view_equipment";
	}
	
}
