package com.yi.spring.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping(value = "/rest")
public class Rest_CustController {
    @GetMapping("/home")
    public String getCustomerRooms(Model model, HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        String chatRoomId = (String) servletContext.getAttribute( "user1" );
        if ( null == chatRoomId )
        {
            chatRoomId = UUID.randomUUID().toString().replace( "-", "" );
            servletContext.setAttribute( "user1", chatRoomId);
        }


        model.addAttribute( "chatRoomId", chatRoomId);
        return "chat/customer";
    }


    @GetMapping("/proc")
    public String getProduceRooms(Model model, HttpServletRequest request, @RequestParam("custno") String name){
        ServletContext servletContext = request.getServletContext();
        String chatRoomId = (String) servletContext.getAttribute( "user" + name );

        model.addAttribute( "chatRoomId", chatRoomId);
        return "chat/producer";
    }
}
