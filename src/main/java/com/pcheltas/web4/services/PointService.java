package com.pcheltas.web4.services;

import com.pcheltas.web4.Checker;
import com.pcheltas.web4.exceptions.InvalidDataException;
import com.pcheltas.web4.model.Point;
import com.pcheltas.web4.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PointService {
    private final PointRepository pointRepository;

    private final Checker checker = new Checker();

    @Autowired
    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public Point setupPoint(Point point){
        long startTime = System.nanoTime();

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        point.setHit(checker.isHit(point.getX(), point.getY(), point.getR()));

        point.setNowTime(formattedDateTime);

        String msTime = String.format("%.6f", ((System.nanoTime() - startTime) / 1_000_000.0)).replace(',', '.');
        point.setExecutionTime(msTime);

        return point;
    }

    public void addPoint(Point point){
        checker.checkValid(point);
        pointRepository.save(point);
    }

    public List<Point> getAllPoints(String username){
        return pointRepository.findPointsByUsername(username);
    }

    public void clear(String username){
        List<Point> points = pointRepository.findPointsByUsername(username);
        for (Point point : points) {
            pointRepository.deleteById(point.getId());
        }

    }
}
