package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBCourseEntity;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Stateless(name = "myCourseENJ")
@Local(com.a20da10.service.ejb.CourseServiceLocal.class)
@Remote(com.a20da10.service.ejb.CourseServiceRemote.class)
public class CourseService implements CourseServiceLocal,CourseServiceRemote {

    @PersistenceContext(name = "DAPU")
    EntityManager em;

    public List<EJBCourseEntity> findStudent(String name){
        Query query = em.createQuery("SELECT p from EJBCourseEntity p WHERE p.name= :searchname",EJBCourseEntity.class);
        query.setParameter("searchname",name);
        return query.getResultList();
    }
}
