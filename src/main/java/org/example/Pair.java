package org.example;

public class Pair<X, Y> {
    private X value1;
    private Y value2;

    public Pair(X value1, Y value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public X getValue1() {
        return value1;
    }

    public Y getValue2() {
        return value2;
    }

    public void setValue1(X value1) {
        this.value1 = value1;
    }

    public void setValue2(Y value2) {
        this.value2 = value2;
    }
}
