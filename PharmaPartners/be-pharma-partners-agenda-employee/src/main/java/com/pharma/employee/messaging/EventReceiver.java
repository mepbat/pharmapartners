package com.pharma.employee.messaging;

import com.google.gson.Gson;
import com.pharma.employee.events.CreateAppointmentEvent;
import com.pharma.employee.messaging.messages.CreateEmployeeMessage;
import com.pharma.employee.models.Employee;
import com.pharma.employee.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EventReceiver {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final Logger log = LoggerFactory.getLogger(EventReceiver.class);

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = "create-employee-queue")
    public void receive(Message message) {
        System.out.println("received the event!");
        log.info("Received event in service Employee: {}", new String(message.getBody()));
        Gson gson = new Gson();
        String json = new String(message.getBody());
        CreateEmployeeMessage event = gson.fromJson(json, CreateEmployeeMessage.class);
        Employee emp = new Employee();
        emp.setId(event.getId());
        emp.setFirstName(event.getName());
        employeeRepository.save(emp);
        // Convert to object.
        // Do with it whatever you please.
    }
}

