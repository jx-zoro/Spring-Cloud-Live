package com.telusko.SpringBootMavenApp.web;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZoroController {

    @GetMapping({"/", "/greeting"})
    public String generateGreeting(
            @RequestParam(name = "userName", required = false, defaultValue = "J.S. Surya") String userName,
            @RequestParam(name = "tier", required = false, defaultValue = "Master Developer") String tier) {

        int hour = LocalTime.now().getHour();
        String timeGreeting;
        if (hour >= 5 && hour < 12) {
            timeGreeting = "Morning";
        } else if (hour >= 12 && hour < 17) {
            timeGreeting = "Afternoon";
        } else {
            timeGreeting = "Evening";
        }

        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long usedMemory = memoryBean.getHeapMemoryUsage().getUsed() / (1024 * 1024);
        long maxMemory = memoryBean.getHeapMemoryUsage().getMax() / (1024 * 1024);
        int activeThreads = Thread.activeCount();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return "<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<title>Spring Boot Enterprise Portal</title>"
                + "<style>"
                + "  * { box-sizing: border-box; margin: 0; padding: 0; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif; }"
                + "  body { background-color: #0b1118; color: #e6edf3; display: flex; justify-content: center; align-items: center; min-height: 100vh; padding: 24px; }"
                + "  .portal-container { background: #121820; border: 1px solid #232d3b; border-radius: 14px; width: 100%; max-width: 780px; padding: 36px; box-shadow: 0 20px 45px rgba(0, 0, 0, 0.75); }"
                + "  .header { display: flex; justify-content: space-between; align-items: flex-start; border-bottom: 1px solid #1f2937; padding-bottom: 24px; margin-bottom: 28px; }"
                + "  .headline h1 { font-size: 28px; font-weight: 700; color: #58a6ff; line-height: 1.35; }"
                + "  .headline p { color: #8b949e; font-size: 13.5px; margin-top: 8px; }"
                + "  .status-badge { display: inline-flex; align-items: center; gap: 7px; background: rgba(35, 134, 54, 0.18); border: 1px solid #238636; color: #3fb950; font-size: 11px; font-weight: 700; padding: 5px 12px; border-radius: 9999px; text-transform: uppercase; letter-spacing: 0.5px; }"
                + "  .status-dot { width: 7px; height: 7px; background: #3fb950; border-radius: 50%; box-shadow: 0 0 8px #3fb950; }"
                + "  .grid { display: grid; grid-template-columns: repeat(2, 1fr); gap: 18px; margin-bottom: 28px; }"
                + "  .card { background: #0b1118; border: 1px solid #1f2a38; border-radius: 10px; padding: 20px; }"
                + "  .card-label { display: flex; align-items: center; gap: 7px; font-size: 11px; font-weight: 700; text-transform: uppercase; letter-spacing: 1.2px; color: #8b949e; margin-bottom: 10px; }"
                + "  .indicator-dot { width: 6px; height: 6px; background: #238636; border-radius: 50%; }"
                + "  .card-val { font-size: 21px; font-weight: 700; color: #f0f6fc; font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, monospace; }"
                + "  .card-val span { color: #8b949e; font-size: 14px; font-weight: 400; }"
                + "  .card-sub { font-size: 12px; color: #58a6ff; margin-top: 6px; }"
                + "  .footer { background: #070c12; border: 1px solid #1c2633; border-radius: 8px; padding: 14px 20px; font-size: 12px; font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, monospace; display: flex; justify-content: space-between; align-items: center; color: #8b949e; }"
                + "  .highlight { color: #3fb950; font-weight: 700; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='portal-container'>"
                + "  <div class='header'>"
                + "    <div class='headline'>"
                + "      <h1>Good " + timeGreeting + ", " + userName + ".<br>Welcome to the Enterprise Portal.</h1>"
                + "      <p>Embedded Tomcat with active Spring IoC dependency architecture & service layers.</p>"
                + "    </div>"
                + "    <div class='status-badge'>"
                + "      <span class='status-dot'></span> Connected Successfully"
                + "    </div>"
                + "  </div>"
                + "  <div class='grid'>"
                + "    <div class='card'>"
                + "      <div class='card-label'><span class='indicator-dot'></span> JVM HEAP MEMORY</div>"
                + "      <div class='card-val'>" + usedMemory + " MB <span>/ " + maxMemory + " MB</span></div>"
                + "      <div class='card-sub'>Active Dynamic Allocation</div>"
                + "    </div>"
                + "    <div class='card'>"
                + "      <div class='card-label'><span class='indicator-dot'></span> ACTIVE JVM THREADS</div>"
                + "      <div class='card-val'>" + activeThreads + " Threads</div>"
                + "      <div class='card-sub'>Multi-threaded Pool</div>"
                + "    </div>"
                + "    <div class='card'>"
                + "      <div class='card-label'><span class='indicator-dot'></span> REQUEST PARAMETERS</div>"
                + "      <div class='card-val'>" + userName + "</div>"
                + "      <div class='card-sub'>DEVELOPER ROLE // " + tier + "</div>"
                + "    </div>"
                + "    <div class='card'>"
                + "      <div class='card-label'><span class='indicator-dot'></span> VIEW ENGINE</div>"
                + "      <div class='card-val'>Tomcat Jasper</div>"
                + "      <div class='card-sub'>greet.jsp restored layout</div>"
                + "    </div>"
                + "  </div>"
                + "  <div class='footer'>"
                + "    <div>SERVER TIME: <span class='highlight'>" + timestamp + "</span></div>"
                + "    <div>PORTAL STATUS: <span class='highlight'>OPERATIONAL</span></div>"
                + "  </div>"
                + "</div>"
                + "</body>"
                + "</html>";
    }
}
