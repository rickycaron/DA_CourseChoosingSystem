package com.a20da10.service.ejb;

import com.a20da10.Entity.spring.CourseEntity;

import java.util.List;

public class InstructorSelfServiceImpl implements InstructorSelfServiceLocal,InstructorSelfServiceRemote{
    @Override
    public List<CourseEntity> getCoursesOfMine(int insId) {
        return null;
    }
}
