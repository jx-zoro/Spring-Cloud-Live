package com.telusko.SpringBootMavenApp.service.IGreetingService;

import java.time.LocalTime;
import org.springframework.stereotype.Service;

@Service
public class GreetingService implements IGreetingService {

    @Override
    public String generateGreeting() {
        LocalTime now = LocalTime.now();
        int hour = now.getHour();

        if (hour < 12) {
            return "Good Morning, J.S. Surya! Welcome to the Enterprise Portal.";
        } else if (hour < 17) {
            return "Good Afternoon, J.S. Surya! Welcome to the Enterprise Portal.";
        } else {
            return "Good Evening, J.S. Surya! Welcome to the Enterprise Portal.";
        }
    }
}