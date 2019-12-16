package weba;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController {
	@Autowired
	private StudentRepo repo;
	
	@RequestMapping("/")
	public String index(Model model)
	{
		List<Student> studs=(List<Student>)repo.findAll();
		model.addAttribute("students",studs);
		return "index";
	}
	@RequestMapping(value="add")
	public String addStudent(Model model)
	{
		model.addAttribute("student", new Student());
		return "addStudent";
	}
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(Student student)
	{
		repo.save(student);
		return "redirect:/";
	}
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id, Model model)
	{
		repo.deleteById(id);
		return "redirect:/";
	}
	@RequestMapping(value="/view/{id}", method= {RequestMethod.GET,RequestMethod.POST})
	public String select(@PathVariable("id") Integer id, Model model)
	{
		Optional<Student> stu=repo.findById(id);
		Student s=stu.get();
		model.addAttribute("studen",s);
		return "viewStudent";
	}
	@RequestMapping(value="edit/{id}", method=RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model)
	{
		Optional<Student> stu=repo.findById(id);
		Student s=stu.get();
		model.addAttribute("student",s);
		return "editStudent";
	}
	@PostMapping(value="/update")
	public String update(@ModelAttribute("student") Student student)
	{
		repo.save(student);
		System.out.println("S-a apasat butonul de Editare");
		return "redirect:/";
	}
	@PostMapping(value="error")
	public void showErr()
	{
		
	}
}
