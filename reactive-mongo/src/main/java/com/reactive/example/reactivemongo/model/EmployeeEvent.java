package com.reactive.example.reactivemongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEvent {

    private Employee employee;
    private Date data;
}
