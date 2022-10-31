package com.ltp.gradesubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GradeController {
    public List<Grade> studentGrades = new ArrayList<>();
    @GetMapping("/grades")
    public String grades(Model model) {
        model.addAttribute("grades", studentGrades);
        System.out.println("lalala");
        return "grades";
    }

    @GetMapping("/")
    public String gradeForm(Model model, @RequestParam(required = false) String id) {
        Grade filteredGrade = new Grade();
        if (id != null) {
            filteredGrade = studentGrades.stream().filter(st -> id.equals(st.getId())).findFirst().orElse(new Grade());
        }
        model.addAttribute("grade", filteredGrade);
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String handleSubmit(Grade grade) {
        studentGrades = studentGrades.stream().filter(gr -> !gr.getId().equals(grade.getId())).collect(Collectors.toList());
        studentGrades.add(grade);
        return "redirect:/grades";
    }
}
