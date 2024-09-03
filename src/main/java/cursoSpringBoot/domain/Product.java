package cursoSpringBoot.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Product {

    private Integer id;
    private String nombre;
    private Double precio;
    private Integer stock;



}
