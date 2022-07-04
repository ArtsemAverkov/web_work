package by.it.academy.entities.product;

import lombok.*;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Builder
@Data
@ToString(exclude = "product")
@EqualsAndHashCode(exclude = "product")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Table(name = "Model_Product")
public class ModelProduct {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Model")
    private String model;

    @Column(name = "Price")
    private int price;

    @Column(name = "Amount")
    private int amount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public String toString() {
        return "ModelProduct{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", product=" + product +
                '}';
    }
}
