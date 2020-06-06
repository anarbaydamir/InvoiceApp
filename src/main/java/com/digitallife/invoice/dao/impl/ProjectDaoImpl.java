package com.digitallife.invoice.dao.impl;

import com.digitallife.invoice.dao.inter.ProjectDaoInter;
import com.digitallife.invoice.entity.Project;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

@Repository("projectDao")
public class ProjectDaoImpl implements ProjectDaoInter {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Project> getAllProjects() {
        String jpql = "Select p from Project p";

        Query query = entityManager.createQuery(jpql,Project.class);
        List<Project> projects = query.getResultList();

        return projects;
    }

    @Override
    public Project getProjectById(int id) {
        Project project = entityManager.find(Project.class,id);

        return project;
    }

    @Override
    public Integer addProject(Project project) {
        entityManager.persist(project);
        entityManager.flush();
        return project.getId();
    }

    @Override
    public boolean updateProject(Project project) {
        try{
            entityManager.merge(project);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteProject(int id) {
        try{
            Project project = entityManager.find(Project.class,id);
            entityManager.remove(project);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
