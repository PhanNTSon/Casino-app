package io.phanson.casino_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/health")
public class ServerController {

    @GetMapping("/server")
    public ResponseEntity<String> getServerHealth() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/taixiu")
    public String getGameTaiXiuHealth() {
        if (this.getGameTaiXiuHealth().equalsIgnoreCase("ok")) {
            return new String("OK");

        } else {
            return new String("BAD");

        }
    }

    @GetMapping("/slot-machine")
    public String getGameSlotMachineHealth() {
        if (this.getGameTaiXiuHealth().equalsIgnoreCase("ok")) {
            return new String("OK");

        } else {
            return new String("BAD");

        }
    }

    @GetMapping("/b52")
    public String getGameB52Health() {
        if (this.getGameTaiXiuHealth().equalsIgnoreCase("ok")) {
            return new String("OK");

        } else {
            return new String("BAD");

        }
    }

}
