package by.it.academy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "imageForModelProduct")
@AllArgsConstructor
@NoArgsConstructor
public class ImageForModelProduct {
    @Id
    private UUID id;
    @ToString.Exclude
    private byte[] image;
}
