package pl.pollub.ISbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Vehicles")
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
// nie wiem jakie pola
}
