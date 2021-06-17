package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBInstructorEntity;
import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.Entity.spring.CourseTypeEnum;
import com.a20da10.dao.ejb.InstructorDao;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;

@Stateful(name = "InstructorSelfServiceImpl")
public class InstructorSelfServiceImpl implements InstructorSelfServiceLocal,InstructorSelfServiceRemote{

    int instructorId;

    int testInt = 0;
    @Inject
    private InstructorDao dao;

    @Override
    public EJBInstructorEntity getMyInfo() {
        return dao.getById(instructorId);
    }

    @Override
    public List<CourseEntity> getCoursesOfMine() {
        return dao.findCoursesByInsId(instructorId);
    }

    @Override
    public void insertInstructor(String firstName, String lasttName, String email, String password, String instructorNumber) {
        dao.create(new EJBInstructorEntity(firstName, lasttName, email, password, instructorNumber));
    }

    @Override
    public void updateInstructor(String firstName, String lastName, String email){
        dao.updateById(this.instructorId, firstName, lastName, email);
    }

    @Override
    public void deleteInstructorByInsId(int insId) {
        dao.deleteById(insId);
    }

    @Override
    public void updateCourseInfo(int courseId, String name, CourseTypeEnum type) {
        dao.updateCourseInfo(courseId, name, this.instructorId, type);
    }

    @Override
    public void addNewCourse(String name, CourseTypeEnum type){
        dao.addNewCourse(name, this.instructorId, type);
    }
    public void setInsId(int id){
        this.instructorId = id;
    }
    public int getInsId(){
        return this.instructorId;
    }
    @Override
    public void deleteCourse(int courseId){
        dao.deleteCourse(courseId);
    }

    public int statefulIncrementTestInt (){
        testInt++;
        return testInt;
    }

}
