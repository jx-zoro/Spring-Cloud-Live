package com.telusko.SpringBootMavenApp.web;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZoroController {

    @GetMapping("/greeting")
    public String generateGreeting(
            @RequestParam(name = "userName", required = false, defaultValue = "J.S. Surya") String userName,
            @RequestParam(name = "tier", required = false, defaultValue = "Master Developer") String tier) {

        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long usedMemory = memoryBean.getHeapMemoryUsage().getUsed() / (1024 * 1024);
        long maxMemory = memoryBean.getHeapMemoryUsage().getMax() / (1024 * 1024);
        int activeThreads = Thread.activeCount();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<title>Spring Boot Live</title>"
                + "<style>"
                + "body { font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; background: #0b0f19; color: #f8fafc; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }"
                + ".card { background: #111827; padding: 40px; border-radius: 16px; box-shadow: 0 20px 25px -5px rgba(0,0,0,0.5); text-align: center; max-width: 520px; width: 90%; border: 1px solid #1f2937; }"
                + "h1 { color: #38bdf8; margin: 0 0 10px 0; font-size: 28px; }"
                + ".badge { background: #2563eb; color: #ffffff; padding: 4px 14px; border-radius: 9999px; font-size: 12px; font-weight: bold; text-transform: uppercase; display: inline-block; margin-bottom: 24px; letter-spacing: 0.5px; }"
                + ".metrics { background: #030712; padding: 18px; border-radius: 10px; text-align: left; font-family: monospace; font-size: 13px; line-height: 2; border: 1px solid #1f2937; }"
                + ".metric-item { display: flex; justify-content: space-between; color: #9ca3af; }"
                + ".metric-value { color: #22c55e; font-weight: bold; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='card'>"
                + "<h1>Hello, " + userName + "!</h1>"
                + "<div class='badge'>" + tier + "</div>"
                + "<div class='metrics'>"
                + "<div class='metric-item'><span>Status:</span> <span class='metric-value'>Online (Render Cloud)</span></div>"
                + "<div class='metric-item'><span>Heap Memory:</span> <span class='metric-value'>" + usedMemory + " MB / " + maxMemory + " MB</span></div>"
                + "<div class='metric-item'><span>Active Threads:</span> <span class='metric-value'>" + activeThreads + "</span></div>"
                + "<div class='metric-item'><span>Server Time:</span> <span class='metric-value'>" + timestamp + "</span></div>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
    }
}
