package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBInstructorEntity;
import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.dao.ejb.InstructorDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "InstructorGenServiceImpl")
public class InstructorGenServiceImpl implements InstructorGenServiceLocal, InstructorGenServiceRemote{

    @Inject
    private InstructorDao dao;

    @PersistenceContext(name="DAPU")
    private EntityManager em;

    @Override
    public List<EJBInstructorEntity> getInstructorByName(String firsName, String lastName) {
        return dao.getByName(firsName, lastName);
    }

    @Override
    public EJBInstructorEntity getInstructorByInsId(int insId) {
        return dao.getById(insId);
    }

    @Override
    public List<EJBInstructorEntity> getAllInstructors() {
        return dao.getAll();
    }

    @Override
    public void insertInstructor(String firstName, String lasttName, String email, String password) {
        dao.create(new EJBInstructorEntity(firstName, lasttName, email, password));
    }

    @Override
    public void updateInstructor(int id, String firstName, String lasttName, String email){
        dao.updateById(id, firstName, lasttName, email);
    }

    @Override
    public void deleteInstructorByInsId(int insId) {
        dao.deleteById(insId);
    }

    @Override
    public List<CourseEntity> getCoursesByInsId(int insId) {
        return dao.findCoursesByInsId(insId);
    }
}
