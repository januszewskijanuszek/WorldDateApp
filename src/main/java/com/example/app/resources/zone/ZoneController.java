package com.example.app.resources.zone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Controller
public class ZoneController {
    private final ZoneService zoneService;

    @Autowired
    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @RequestMapping(value = "/zone")
    public String getTime(Model model) throws IOException {
        File file = new File("src/main/java/com/example/app/config.txt");
        Scanner scanner = new Scanner(file);
        model.addAttribute("time", zoneService.getTime());
        model.addAttribute("name", "Time for: " + scanner.nextLine());
        return "zone";
    }
}
