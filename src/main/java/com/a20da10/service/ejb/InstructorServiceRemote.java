package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBInstructorsEntity;

import java.util.List;

public interface InstructorServiceRemote {
    public List<EJBInstructorsEntity> getAllInstructors ();
}
