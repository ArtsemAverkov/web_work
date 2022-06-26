package by.it.academy.entities.product;

import lombok.*;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = "modelProductSet")
@EqualsAndHashCode(exclude = "modelProductSet")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private  Long id;

    @Column(name = "Product_Name")
    private String name;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ModelProduct> modelProductSet = new HashSet<>();

    public Product(String name) {
        this.name = name;
    }

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
