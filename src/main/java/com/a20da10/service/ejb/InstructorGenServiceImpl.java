package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBInstructorEntity;
import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.dao.ejb.InstructorDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;

@WebService
@Stateless(name = "InstructorGenServiceImpl")
public class InstructorGenServiceImpl implements InstructorGenServiceLocal, InstructorGenServiceRemote{

    @Inject
    private InstructorDao insDao;

    @Override
    public List<EJBInstructorEntity> getInstructorByName(String firsName, String lastName) {
        return insDao.getByName(firsName, lastName);
    }

    @Override
    public EJBInstructorEntity getInstructorByInsId(int insId) {
        return insDao.getById(insId);
    }

    @Override
    public List<EJBInstructorEntity> getAllInstructors() {
        return insDao.getAll();
    }

    @Override
    public List<CourseEntity> getCoursesByInsId(int insId) {
        return insDao.findCoursesByInsId(insId);
    }

    @Override
    public CourseEntity getCourseById(int courseid) {
        return insDao.findCoursesByCourseId(courseid);
    }

    @Override
    public void updateIns(EJBInstructorEntity instructorEntity) {
        dao.updateEntity(instructorEntity);
    }

}
