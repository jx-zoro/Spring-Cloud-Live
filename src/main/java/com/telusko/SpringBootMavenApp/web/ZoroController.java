package com.telusko.SpringBootMavenApp.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class ZoroController {

    @GetMapping({"/", "/greeting"})
    public String generateGreeting(
            @RequestParam(name = "username", required = false, defaultValue = "J.S. Surya") String username,
            @RequestParam(name = "tier", required = false, defaultValue = "Master Developer") String tier) {

        int hour = LocalDateTime.now().getHour();
        String timeGreeting;
        if (hour < 12) {
            timeGreeting = "Good Morning";
        } else if (hour < 17) {
            timeGreeting = "Good Afternoon";
        } else {
            timeGreeting = "Good Evening";
        }

        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long usedMemory = memoryBean.getHeapMemoryUsage().getUsed() / (1024 * 1024);
        long maxMemory = memoryBean.getHeapMemoryUsage().getMax() / (1024 * 1024);
        int activeThreads = Thread.activeCount();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return "<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "<title>Spring Boot Enterprise Portal</title>" +
                "<style>" +
                "* { box-sizing: border-box; margin: 0; padding: 0; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; }" +
                "body { background-color: #0b1118; color: #e6edf3; display: flex; justify-content: center; align-items: center; min-height: 100vh; padding: 20px; }" +
                ".portal-container { background: #121820; border: 1px solid #232d3b; border-radius: 12px; width: 100%; max-width: 650px; padding: 24px; box-shadow: 0 10px 30px rgba(0,0,0,0.5); }" +
                ".header { display: flex; justify-content: space-between; align-items: flex-start; margin-bottom: 20px; }" +
                ".headline h1 { font-size: 28px; font-weight: 700; color: #58a6ff; line-height: 1.2; }" +
                ".headline p { color: #8b949e; font-size: 13.5px; margin-top: 8px; }" +
                ".status-badge { display: inline-flex; align-items: center; gap: 7px; background: rgba(56, 139, 253, 0.1); border: 1px solid rgba(56, 139, 253, 0.3); border-radius: 20px; padding: 6px 12px; font-size: 12px; color: #58a6ff; font-weight: 600; }" +
                ".status-dot { width: 7px; height: 7px; background: #3fb950; border-radius: 50%; box-shadow: 0 0 8px #3fb950; }" +
                ".grid { display: grid; grid-template-columns: repeat(2, 1fr); gap: 14px; margin: 20px 0; }" +
                ".card { background: #0b1118; border: 1px solid #1f2a38; border-radius: 10px; padding: 14px; }" +
                ".card-label { display: flex; align-items: center; gap: 7px; font-size: 11px; color: #8b949e; text-transform: uppercase; letter-spacing: 0.5px; }" +
                ".indicator-dot { width: 6px; height: 6px; background: #238636; border-radius: 50%; }" +
                ".card-val { font-size: 21px; font-weight: 700; color: #f0f6fc; font-family: monospace; margin-top: 8px; }" +
                ".card-val span { color: #8b949e; font-size: 14px; font-weight: 400; }" +
                ".card-sub { font-size: 12px; color: #58a6ff; margin-top: 6px; }" +
                ".footer { background: #070c12; border: 1px solid #1c2633; border-radius: 8px; padding: 12px; font-size: 12px; color: #8b949e; display: flex; justify-content: space-between; }" +
                ".highlight { color: #3fb950; font-weight: 700; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='portal-container'>" +
                "<div class='header'>" +
                "<div class='headline'>" +
                "<h1>" + timeGreeting + ", " + username + "</h1>" +
                "<p>Embedded Tomcat with active Spring IoC dependency architecture & service layer.</p>" +
                "</div>" +
                "<div class='status-badge'>" +
                "<span class='status-dot'></span> Connected Successfully" +
                "</div>" +
                "</div>" +
                "<div class='grid'>" +
                "<div class='card'>" +
                "<div class='card-label'><span class='indicator-dot'></span> JVM Heap Memory</div>" +
                "<div class='card-val'>" + usedMemory + " <span>MB</span> / " + maxMemory + " <span>MB</span></div>" +
                "<div class='card-sub'>Active Dynamic Allocation</div>" +
                "</div>" +
                "<div class='card'>" +
                "<div class='card-label'><span class='indicator-dot'></span> Active JVM Threads</div>" +
                "<div class='card-val'>" + activeThreads + " <span>Threads</span></div>" +
                "<div class='card-sub'>Multi-Threaded Pool</div>" +
                "</div>" +
                "<div class='card'>" +
                "<div class='card-label'><span class='indicator-dot'></span> Request Parameters</div>" +
                "<div class='card-val'>" + username + "</div>" +
                "<div class='card-sub'>DEVELOPER ROLE // " + tier + "</div>" +
                "</div>" +
                "<div class='card'>" +
                "<div class='card-label'><span class='indicator-dot'></span> Server Time</div>" +
                "<div class='card-val' style='font-size:15px;'>" + timestamp + "</div>" +
                "<div class='card-sub'>Standard UTC System Time</div>" +
                "</div>" +
                "</div>" +
                "<div class='footer'>" +
                "<span>Status: <span class='highlight'>ONLINE (200 OK)</span></span>" +
                "<span>Containerized Web Service</span>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";
    }
}
