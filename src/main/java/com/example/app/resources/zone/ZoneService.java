package com.example.app.resources.zone;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;

@Service
public class ZoneService {

    public ZoneDate getTime() throws IOException {
        ZoneConfigurator zoneConfigurator = new ZoneConfigurator(new File("src/main/java/com/example/app/config.txt"));
        return zoneConfigurator.getZoneDate();
    }
}
