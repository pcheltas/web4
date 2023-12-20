package com.pcheltas.web4.controller;

import com.pcheltas.web4.model.Point;
import com.pcheltas.web4.services.PointService;
import com.pcheltas.web4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/")
public class PointController {
    private final PointService pointService;
    private final UserService userService;


    @Autowired
    public PointController(PointService pointService, UserService userService) {
        this.pointService = pointService;
        this.userService = userService;
    }


    @GetMapping("/points")
    public List<Point> getAllPoints(@RequestHeader("username") String username, @RequestHeader("token") String token) {
        userService.checkUser(userService.userInit(username, token));
        return pointService.getAllPoints(username);
    }

    @PostMapping(value = "/points")
    public ResponseEntity<?> createPoint(Point point, @RequestHeader("username") String username, @RequestHeader("token") String token) {
        userService.checkUser(userService.userInit(username, token));

        point.setUsername(username);
        point = pointService.setupPoint(point);
        pointService.addPoint(point);

        return new ResponseEntity<>("added successfully", HttpStatus.CREATED);
    }


    @DeleteMapping("/points")
    public ResponseEntity<?> clearAllShots(@RequestHeader("username") String username, @RequestHeader("token") String token) {
        userService.checkUser(userService.userInit(username, token));

        pointService.clear(username);

        return new ResponseEntity<>("deleted successfully", HttpStatus.ACCEPTED);
    }
}