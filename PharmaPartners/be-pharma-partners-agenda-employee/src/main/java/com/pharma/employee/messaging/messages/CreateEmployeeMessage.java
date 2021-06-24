package com.pharma.employee.messaging.messages;

public class CreateEmployeeMessage {
    private Long id;
    private String name;

    public CreateEmployeeMessage() {
    }

    public CreateEmployeeMessage(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
