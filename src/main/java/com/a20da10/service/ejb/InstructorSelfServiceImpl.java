package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBInstructorEntity;
import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.dao.ejb.InstructorDao;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.inject.Inject;
import java.util.List;


@Stateful(name = "InstructorSelfServiceImpl")
@StatefulTimeout(value = 30)
public class InstructorSelfServiceImpl implements InstructorSelfServiceLocal,InstructorSelfServiceRemote{

    int instructorId;

    @Inject
    private InstructorDao insDao;

    @Override
    public EJBInstructorEntity getMyInfo() {
        return insDao.getById(instructorId);
    }

    @Override
    public List<CourseEntity> getCoursesOfMine() {
        return insDao.findCoursesByInsId(instructorId);
    }

    @Override
    public void insertInstructor(String firstName, String lasttName, String email, String password, String instructorNumber) {
        insDao.create(new EJBInstructorEntity(firstName, lasttName, email, password, instructorNumber));
    }

    @Override
    public void updateInstructor(EJBInstructorEntity instructorEntity) {
        insDao.updateIns(instructorEntity);
    }

    @Override
    public void deleteInstructorByInsId(int insId) {
        insDao.deleteById(insId);
    }

    @Override
    public void addNewCourse(CourseEntity courseEntity){
        insDao.addNewCourse(courseEntity);
    }
    public void setInsId(int id){
        this.instructorId = id;
    }
    public int getInsId(){
        return this.instructorId;
    }
    @Override
    public void deleteCourse(int courseId){
        insDao.deleteCourse(courseId);
    }

    @Override
    public void updateCourseInfo(CourseEntity courseEntity) {
        insDao.updateCourseInfo(courseEntity);
    }

    @Override
    @Remove
    public void removeBean(){
        instructorId = -1;
        insDao = null;
    }
}
