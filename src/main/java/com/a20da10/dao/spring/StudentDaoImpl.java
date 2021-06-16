package com.a20da10.dao.spring;

import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.Entity.spring.StudentEntity;
import com.a20da10.Entity.spring.TextMessageEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DependsOn("sessionFactory")
public class StudentDaoImpl implements StudentDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<StudentEntity> getAllStudents() {
//        1.get the current hibernate session
        Session session = sessionFactory.getCurrentSession();
//        2.create sql query
        Query<StudentEntity> query = session.createQuery("from StudentEntity ",StudentEntity.class);
//        3.execute sql query
        List<StudentEntity> students = query.getResultList();

        System.out.println("return all the students");
        return students;
    }
    @Override
    public void updateStudent(StudentEntity studentEntity) {
        Session session =sessionFactory.getCurrentSession();
        session.saveOrUpdate(studentEntity);
    }

    @Override
    public void deleteStudent(Integer studentId) {
        Session session=sessionFactory.getCurrentSession();
        Query query =session.createQuery("delete from StudentEntity where id=:studentId");
        query.setParameter("studentId",studentId);
        query.executeUpdate();
    }

    @Override
    public void subscribeCourse(StudentEntity studentEntity, CourseEntity courseEntity) {
        studentEntity.addCourse(courseEntity);
    }

    @Override
    public void cancleCourse(StudentEntity studentEntity, CourseEntity courseEntity) {

            studentEntity.getCourseEntities().removeIf(e->e.getCourseId()==studentEntity.getStudentId());

    }


    @Override
    public void addStudent(StudentEntity studentEntity) {
        if (studentEntity!=null){
            Session session = sessionFactory.getCurrentSession();
            session.save(studentEntity);
        }

    }

    @Override
    public StudentEntity getStudentEntity(Integer studentId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(StudentEntity.class,studentId);
    }
    @Override
    public StudentEntity getStudentEntityByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query =session.createQuery("select s from StudentEntity s where s.email = :email",StudentEntity.class);
        query.setParameter("email",email);
        System.out.println("email:"+ email);
        return (StudentEntity) query.getSingleResult();
    }
}
