package com.a20da10.dao.spring;

import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.Entity.spring.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<StudentEntity> getAllStudents() {
//        1.get the current hibernate session
        Session session = sessionFactory.getCurrentSession();
//        2.create sql query
        Query<StudentEntity> query = session.createQuery("from StudentEntity ",StudentEntity.class);
//        3.execute sql query
        List<StudentEntity> students = query.getResultList();
        return students;
    }

    @Override
    @Transactional
    public void subscribeCourse(StudentEntity studentEntity, CourseEntity courseEntity) {
        studentEntity.addCourse(courseEntity);
    }

    @Override
    @Transactional
    public void addStudent(StudentEntity studentEntity) {
        if (studentEntity!=null){
            Session session = sessionFactory.getCurrentSession();
            session.save(studentEntity);
        }

    }

    @Override
    @Transactional
    public StudentEntity getStudentEntity(Integer studentId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(StudentEntity.class,studentId);
    }

}
