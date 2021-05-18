package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBCourseEntity;

import java.util.List;

public interface CourseServiceRemote {
    public List<EJBCourseEntity> findStudent(String name);

}
