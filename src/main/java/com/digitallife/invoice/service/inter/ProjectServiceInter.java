package com.digitallife.invoice.service.inter;

import com.digitallife.invoice.entity.Project;

import java.util.List;

public interface ProjectServiceInter {
    public List<Project> getAllProjects();

    public Project getProjectById(int id);

    public Integer addProject(Project project);

    public boolean updateProject(Project project);

    public boolean deleteProject(int id);
}
