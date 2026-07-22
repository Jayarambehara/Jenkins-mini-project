package com.taskflow.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DashboardServlet extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        System.out.println("DashboardServlet initialized successfully!");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Set response content type
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        // Get the current timestamp
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);
        
        // Set attributes for the JSP/HTML
        request.setAttribute("currentTime", timestamp);
        request.setAttribute("appName", "TaskFlow Dashboard");
        
        // Forward to index.html
        request.getRequestDispatcher("/index.html").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Handle POST requests
        String action = request.getParameter("action");
        
        if ("addTask".equals(action)) {
            // Handle adding a new task
            String taskName = request.getParameter("taskName");
            String priority = request.getParameter("priority");
            
            // You can add business logic here
            System.out.println("New task added: " + taskName + " (" + priority + ")");
            
            // Redirect back to dashboard
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            doGet(request, response);
        }
    }
    
    @Override
    public void destroy() {
        System.out.println("DashboardServlet destroyed.");
    }
}
