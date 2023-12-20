package com.pcheltas.web4;

import com.pcheltas.web4.exceptions.InvalidDataException;
import com.pcheltas.web4.model.Point;

public class Checker {

    public void checkValid(Point point) {
        if (point.getR() <= 0) {
            throw new InvalidDataException("R must be greater than zero");
        }

    }

    public boolean isHit(float x, float y, float R) {
        return isInCircle(x, y, R) || isInRect(x, y, R) || isInTriangle(x, y, R);
    }

    public boolean isInCircle(float x, float y, float R) {
        return x >= 0 && y <= 0 && x * x + y * y <= R * R;
    }

    public boolean isInRect(float x, float y, float R) {
        return x <= 0 && y <= 0 && x >= -R && y >= -R;
    }

    public boolean isInTriangle(float x, float y, float R) {
        return x >= 0 && y >= 0 && y <= -x + R / 2;
    }
}
