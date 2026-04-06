package org.example.entities;

public class test {
    private int id;
    private String name;

    public test(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public test() {
    }

    public test(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "test{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

