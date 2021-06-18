package com.a20da10.dao.ejb;

import com.a20da10.Entity.ejb.EJBInstructorEntity;
import com.a20da10.Entity.spring.CourseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
public class InstructorDaoImpl implements InstructorDao {

    @PersistenceContext(name="DAPU")
    private EntityManager em;

    @Override
    @Transactional
    public void create(EJBInstructorEntity entity) {
        em.persist(entity);
    }


    @Override
    public void updateIns(EJBInstructorEntity instructorEntity) {
        em.merge(instructorEntity);
    }

    @Override
    public List<EJBInstructorEntity> getByName(String firsName, String lastName) {
        Query query =em.createQuery("SELECT i FROM  EJBInstructorEntity i WHERE i.firstName = :firstName AND i.lastName = :lastName", EJBInstructorEntity.class);
        query.setParameter("firstName",firsName);
        query.setParameter("lastName",lastName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public EJBInstructorEntity getById(int id) {
        return em.find(EJBInstructorEntity.class,id);
    }

    @Override
    public EJBInstructorEntity getByEmail(String email) {
        Query query = em.createQuery("SELECT i FROM EJBInstructorEntity i where i.email = :email", EJBInstructorEntity.class);
        query.setParameter("email",email);
        return (EJBInstructorEntity) query.getSingleResult();
    }

    @Override
    @Transactional
    public List<EJBInstructorEntity> getAll() {
        Query query = em.createQuery("SELECT p FROM EJBInstructorEntity p");
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        EJBInstructorEntity i = em.find(EJBInstructorEntity.class,id);
        em.remove(i);
    }

    @Override
    @Transactional
    public List<CourseEntity> findCoursesByInsId(int insId) {
        Query query = em.createQuery("SELECT c FROM CourseEntity c WHERE c.instructorId = :insId");
        query.setParameter("insId", insId);
        return query.getResultList();
    }

    @Override
    public CourseEntity findCoursesByCourseId(int courseId) {
        return em.find(CourseEntity.class, courseId);
    }

    @Override
    @Transactional
    public void updateCourseInfo(CourseEntity courseEntity){
        em.merge(courseEntity);
    }

    @Override
    public void addNewCourse(CourseEntity c) {
        em.persist(c);
    }

    @Override
    public void deleteCourse(int courseId) {
        CourseEntity c = em.find(CourseEntity.class,courseId);
        em.remove(c);
    }

    @Override
    public void initEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DAPU");
        this.em = entityManagerFactory.createEntityManager();
    }

}



