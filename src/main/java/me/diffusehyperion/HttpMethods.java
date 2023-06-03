package me.diffusehyperion;

public enum HttpMethods {
    GET("GET"),
    PUT("PUT"),
    POST("POST"),
    PATCH("PATCH"),
    DELETE("DELETE");

    private final String name;

    HttpMethods(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
