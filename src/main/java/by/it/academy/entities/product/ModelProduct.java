package by.it.academy.entities.product;

import lombok.*;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

@Builder
@Data
@ToString()
@EqualsAndHashCode(exclude = "product")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Table(name = "model_Product")
public class ModelProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String model;
    private int price;
    private int amount;
    private String operatingSystem;
    private String display;
    private String camera;
    private String cpu;
    private String memory;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @ToString.Exclude
    private Product product;


}
