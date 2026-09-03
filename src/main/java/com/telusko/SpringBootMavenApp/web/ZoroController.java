package com.telusko.SpringBootMavenApp.web;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.telusko.SpringBootMavenApp.service.IGreetingService;

@Controller
public class ZoroController {

    @Autowired(required = false)
    private IGreetingService service;

    @GetMapping("/greeting")
    public String generateGreeting(
            @RequestParam(name = "userName", required = false, defaultValue = "J.S. Surya") String userName,
            @RequestParam(name = "tier", required = false, defaultValue = "Master Developer") String tier,
            Model model) {

        String greeting = (service != null) ? service.generateGreeting(userName) : ("Welcome, " + userName);

        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long usedMemory = memoryBean.getHeapMemoryUsage().getUsed() / (1024 * 1024);
        long maxMemory = memoryBean.getHeapMemoryUsage().getMax() / (1024 * 1024);
        int activeThreads = Thread.activeCount();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        model.addAttribute("greeting", greeting);
        model.addAttribute("userName", userName);
        model.addAttribute("tier", tier);
        model.addAttribute("usedMemory", usedMemory);
        model.addAttribute("maxMemory", maxMemory);
        model.addAttribute("activeThreads", activeThreads);
        model.addAttribute("timestamp", timestamp);

        return "greet";
    }
}
