package by.it.academy.entities.product;

import lombok.*;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

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
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Model")
    private String model;

    @Column(name = "Price")
    private int price;

    @Column(name = "Amount")
    private int amount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    public ModelProduct(Product product, String model, int price) {
        this.product = product;
        this.model = model;
        this.price = price;
    }

    public ModelProduct(Product product, String model, int price, int amount) {
        this.model = model;
        this.price = price;
        this.amount = amount;
        this.product = product;
    }


    public ModelProduct(String model, int price, int amount, Product product) {
        this.model = model;
        this.price = price;
        this.amount = amount;
        this.product = product;
    }



    public ModelProduct(Long id) {
        this.id = id;
    }

    public ModelProduct(String name, int price, Product product) {
        this.model = name;
        this.price = price;
        this.product = product;
    }

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
