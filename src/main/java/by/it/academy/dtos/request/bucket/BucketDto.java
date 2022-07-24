package by.it.academy.dtos.request.bucket;

import by.it.academy.entities.product.ModelProduct;
import by.it.academy.entities.user.User;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BucketDto {
    private Long id;
    @NotNull
    private ModelProduct modelProduct;
    @NotNull
    private User user;
}
