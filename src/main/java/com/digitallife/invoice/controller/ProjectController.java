package com.digitallife.invoice.controller;

import com.digitallife.invoice.entity.Project;
import com.digitallife.invoice.service.inter.ProjectServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    private ProjectServiceInter projectServiceInter;

    @GetMapping(value = "project")
    public ModelAndView getProject(){
        List<Project> projects = projectServiceInter.getAllProjects();

        ModelAndView mv = new ModelAndView("project");
        mv.addObject("projects",projects);

        return mv;
    }

    @PostMapping(value = "project")
    public ModelAndView postProject(@RequestParam(name = "id") Integer id,
                                   @RequestParam(name = "action") String action,
                                   @RequestParam(name = "name") String name,
                                    RedirectAttributes redirectAttributes){

        Project project = new Project(id,name);
        try {
            if (action.equals("update")){
                projectServiceInter.updateProject(project);
            }
            else if (action.equals("insert")){
                projectServiceInter.addProject(project);
            }
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("message","error");
        }
        redirectAttributes.addFlashAttribute("message","success");
        ModelAndView mv = new ModelAndView("redirect:/project");
        return mv;
    }

    @PostMapping(value = "project/delete")
    public ModelAndView deleteProject(@RequestParam(name = "id") Integer id,
                                      RedirectAttributes redirectAttributes){
        try {
            projectServiceInter.deleteProject(id);
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("message","error");
        }
        redirectAttributes.addFlashAttribute("message","success");
        ModelAndView mv = new ModelAndView("redirect:/project");
        return mv;
    }
}
