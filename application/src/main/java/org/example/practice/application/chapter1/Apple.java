package org.example.practice.application.chapter1;

import java.util.Objects;

public class Apple {
    private final int weight;

    public Apple(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Apple apple = (Apple) object;
        return weight == apple.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(weight);
    }
}