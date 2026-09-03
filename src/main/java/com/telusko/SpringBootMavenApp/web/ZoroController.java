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

import com.telusko.SpringBootMavenApp.service.IGreetingService.IGreetingService;

@Controller
public class ZoroController {

    @Autowired
    private IGreetingService service;

    @GetMapping("/greeting")
    public String generateWish(
            @RequestParam(name = "userName", required = false, defaultValue = "J.S. Surya") String userName,
            @RequestParam(name = "tier", required = false, defaultValue = "Master Developer") String tier,
            Model model) {

        // Service greeting logic
        String res = service.generateGreeting();
        
        // JVM Runtime Telemetry for dynamic UI metrics
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long usedHeapMB = memoryBean.getHeapMemoryUsage().getUsed() / (1024 * 1024);
        long maxHeapMB = memoryBean.getHeapMemoryUsage().getMax() / (1024 * 1024);
        int activeThreads = Thread.activeCount();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Passing Model Attributes to greet.jsp
        model.addAttribute("wish", res);
        model.addAttribute("userName", userName);
        model.addAttribute("tier", tier);
        model.addAttribute("usedMemory", usedHeapMB);
        model.addAttribute("maxMemory", maxHeapMB);
        model.addAttribute("activeThreads", activeThreads);
        model.addAttribute("timestamp", timestamp);

        return "greet";
    }
}