package com.a20da10.controller;

import com.a20da10.service.ejb.InstructorSelfServiceLocal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJBSOAPController {
    @Inject
    private InstructorSelfServiceLocal instructorSelfServiceLocal;




    {
        try {
            InitialContext ic = new InitialContext();
            instructorSelfServiceLocal = (InstructorSelfServiceLocal)ic.lookup("java:global/a20da10/InstructorSelfServiceImpl");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @GetMapping("/StatefulTestInt")
    public int statefulTestInt(){
        return instructorSelfServiceLocal.incrementTestInt();
    }


}
