package by.it.academy.dtos.request.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertProductRequest {
    @NotBlank
    private String product;
    @NotBlank
    private String model;
    @NotBlank
    private int price;
    @NotBlank
    private int amount;
    @NotBlank
    private String operatingSystem;
    @NotBlank
    private String display;
    @NotBlank
    private String camera;
    @NotBlank
    private String cpu;
    @NotBlank
    private String memory;
    @NotBlank
    private String description;


}
