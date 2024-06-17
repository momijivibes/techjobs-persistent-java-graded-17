package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("skills")
public class SkillController {

    // To do database operations
    @Autowired
    private SkillRepository skillRepository;

    // Index to list all skills
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "All Skills");
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";
    }

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        // Add a new empty Skill object to model
        model.addAttribute(new Skill());
        return "skills/add";
    }

    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                      Errors errors, Model model) {

        //Setting up the model attributes for displaying the 'Add Skill' form, including handling validation errors.E
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Skill");
            model.addAttribute(newSkill);
            return "skills/add";
        }

        // Save the valid skill object to the database
        skillRepository.save(newSkill);

        // Redirect
        return "redirect:";
    }

//    @GetMapping("/{skillId}")
//    public Skill displayViewSkill(Model model, int skillId) {
//        return skillRepository.findById(skillId).orElse(null);
//    }



}
