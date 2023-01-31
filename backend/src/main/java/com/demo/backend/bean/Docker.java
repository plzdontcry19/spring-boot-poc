package com.demo.backend.bean;

public class Docker {

    private String name;
    private Nurse nurse;

    public Docker(String name, Nurse nurse) {
        this.name = name;
        this.nurse = nurse;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void heal() {
        System.out.println("Docker is healing");
    }
}
