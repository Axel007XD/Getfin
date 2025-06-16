// Producto.java
package org.getfin.modelos;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Products")
@Data
@NoArgsConstructor

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaProducto categoria;

    @Column(nullable = true)
    private BigDecimal ivaPorcentaje;

    @Column(nullable = true)
    private BigDecimal retencionPorcentaje;

    public enum CategoriaProducto {cosechado, animal, agroindustriado}

    @OneToMany(mappedBy = "producto")
    private List<MovimientoInventario> movimientos;

    @OneToMany(mappedBy = "producto")
    private List<DetalleIngreso> detallesIngresos;

    public Producto(
            String nombre,
            CategoriaProducto categoria,
            BigDecimal ivaPorcentaje,
            BigDecimal retencionPorcentaje
    ) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.ivaPorcentaje = ivaPorcentaje;
        this.retencionPorcentaje = retencionPorcentaje;
    }

}
