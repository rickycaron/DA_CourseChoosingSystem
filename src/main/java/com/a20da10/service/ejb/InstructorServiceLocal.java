package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBInstructorsEntity;

import java.util.List;

public interface InstructorServiceLocal {
    public List<EJBInstructorsEntity> getAllInstructors ();

}
