package com.baeldung.model;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Employee {
    
    int id;
    String firstName;
    String lastName;
    String email;
}
