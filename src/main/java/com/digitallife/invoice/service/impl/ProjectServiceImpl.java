package com.digitallife.invoice.service.impl;

import com.digitallife.invoice.dao.inter.ProjectDaoInter;
import com.digitallife.invoice.entity.Project;
import com.digitallife.invoice.service.inter.ProjectServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectServiceInter {

    @Autowired
    @Qualifier("projectDao")
    private ProjectDaoInter projectDaoInter;

    @Override
    public List<Project> getAllProjects() {
        return projectDaoInter.getAllProjects();
    }

    @Override
    public Project getProjectById(int id) {
        return projectDaoInter.getProjectById(id);
    }

    @Override
    public Integer addProject(Project project) {
        return projectDaoInter.addProject(project);
    }

    @Override
    public boolean updateProject(Project project) {
        return projectDaoInter.updateProject(project);
    }

    @Override
    public boolean deleteProject(int id) {
        return projectDaoInter.deleteProject(id);
    }
}
