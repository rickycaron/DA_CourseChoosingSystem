package com.example.DistributedApplication;

import com.a20da10.service.ejb.MyTimerService;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "MSGServlet", value = "/MSGServlet")
public class MSGServlet extends HttpServlet {

    @Resource(mappedName = "jms/myMsgDest") //Map the queue with destination assigned in the JMS
    private Queue myMsgDest;
    @Resource(mappedName = "jms/myMsg") //Provide a connection factory object for the queue
    private ConnectionFactory queue;


    @EJB
    MyTimerService timer;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {

        try{
            String str = request.getParameter("t1");
            PrintWriter out = response.getWriter();
            timer.setTimer(5000);
            sendJMSMessageToDest(str);
            out.println("<h1>Your name is Queued</h1>");
        } catch (IOException e) {
            System.out.println(e);;
        }
    }

    private void sendJMSMessageToDest(String messageData){
        try {
            Connection con = queue.createConnection();
            Session s = con.createSession();
            MessageProducer mp = s.createProducer((Destination) myMsgDest);
            TextMessage tm = s.createTextMessage();
            tm.setText(messageData);
            mp.send(tm);
        }
        catch (Exception e){
            System.out.println(e);;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
