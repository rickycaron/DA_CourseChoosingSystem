package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBInstructorsEntity;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
@Stateless(name = "InstructorServiceImpl")
@Remote(com.a20da10.service.ejb.InstructorServiceRemote.class)
@Local(com.a20da10.service.ejb.InstructorServiceLocal.class)
public class InstructorServiceImpl implements InstructorServiceLocal,InstructorServiceRemote{

    @PersistenceContext(name="DAPU")
    private EntityManager em;
    @Override
    public List<EJBInstructorsEntity> getAllInstructors() {
        Query query = em.createQuery("SELECT p FROM EJBInstructorsEntity p");
        return query.getResultList();
    }
}
