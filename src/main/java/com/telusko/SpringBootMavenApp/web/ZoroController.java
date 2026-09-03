package com.telusko.SpringBootMavenApp.web;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telusko.SpringBootMavenApp.service.IGreetingService;

@Controller
public class ZoroController {

    @Autowired(required = false)
    private IGreetingService service;

    @GetMapping("/greeting")
    @ResponseBody
    public String generateGreeting(
            @RequestParam(name = "userName", required = false, defaultValue = "J.S. Surya") String userName,
            @RequestParam(name = "tier", required = false, defaultValue = "Master Developer") String tier) {

        String greetingMessage = (service != null) ? service.generateGreeting(userName) : ("Hello, " + userName + "!");

        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long usedMemory = memoryBean.getHeapMemoryUsage().getUsed() / (1024 * 1024);
        long maxMemory = memoryBean.getHeapMemoryUsage().getMax() / (1024 * 1024);
        int activeThreads = Thread.activeCount();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>Spring Boot Live</title>"
                + "<style>"
                + "body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #0f172a; color: #f8fafc; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }"
                + ".card { background: #1e293b; padding: 35px 45px; border-radius: 16px; box-shadow: 0 10px 25px rgba(0,0,0,0.5); text-align: center; max-width: 550px; width: 90%; border: 1px solid #334155; }"
                + "h1 { color: #38bdf8; margin-bottom: 8px; font-size: 26px; }"
                + ".badge { background: #3b82f6; color: white; padding: 4px 12px; border-radius: 9999px; font-size: 13px; font-weight: bold; text-transform: uppercase; display: inline-block; margin-bottom: 20px; }"
                + ".metrics { background: #0f172a; padding: 16px; border-radius: 8px; text-align: left; margin-top: 20px; font-family: monospace; font-size: 13px; line-height: 1.8; border: 1px solid #1e293b; }"
                + ".metric-item { display: flex; justify-content: space-between; color: #94a3b8; }"
                + ".metric-value { color: #4ade80; font-weight: bold; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='card'>"
                + "<h1>" + greetingMessage + "</h1>"
                + "<div class='badge'>" + tier + "</div>"
                + "<div class='metrics'>"
                + "<div class='metric-item'><span>Status:</span> <span class='metric-value'>Online (Render)</span></div>"
                + "<div class='metric-item'><span>Heap Memory:</span> <span class='metric-value'>" + usedMemory + " MB / " + maxMemory + " MB</span></div>"
                + "<div class='metric-item'><span>Active Threads:</span> <span class='metric-value'>" + activeThreads + "</span></div>"
                + "<div class='metric-item'><span>Timestamp:</span> <span class='metric-value'>" + timestamp + "</span></div>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
    }
}
