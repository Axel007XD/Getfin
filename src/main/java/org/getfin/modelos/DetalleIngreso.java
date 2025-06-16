// DetalleIngreso.java
package org.getfin.modelos;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.getfin.modelos.Animal;
import org.getfin.modelos.AnimalAgrupado;
import org.getfin.modelos.Ingreso;
import org.getfin.modelos.Producto;
import java.math.BigDecimal;

@Entity
@Table(name = "detalles_ingresos")
@Data
@NoArgsConstructor
public class DetalleIngreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleIngreso;

    @ManyToOne @JoinColumn(name = "id_ingreso", nullable = false)
    private Ingreso ingreso;

    @ManyToOne @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne @JoinColumn(name = "id_animal")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "id_agrupacion")
    private AnimalAgrupado agrupacion;

    @Column(nullable = false)
    private BigDecimal cantidad;

    private String unidad;

    @Column()
    private BigDecimal precioUnitario;

    @Column()
    private BigDecimal ivaPorcentaje;

    @Column()
    private BigDecimal retencionPorcentaje;

    @Column()
    private BigDecimal subtotalBase;

    @Column(nullable = false)
    private BigDecimal subtotalIva;

    @Column()
    private BigDecimal subtotalRetencion;

    @Column()
    private BigDecimal subtotalFinal;

    public DetalleIngreso(
            Ingreso ingreso,
            Producto producto,
            Animal animal,
            AnimalAgrupado agrupacion,
            BigDecimal cantidad,
            String unidad,
            BigDecimal precioUnitario,
            BigDecimal ivaPorcentaje,
            BigDecimal retencionPorcentaje,
            BigDecimal subtotalBase,
            BigDecimal subtotalIva,
            BigDecimal subtotalRetencion,
            BigDecimal subtotalFinal
    ) {
        this.ingreso = ingreso;
        this.producto = producto;
        this.animal = animal;
        this.agrupacion = agrupacion;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.precioUnitario = precioUnitario;
        this.ivaPorcentaje = ivaPorcentaje;
        this.retencionPorcentaje = retencionPorcentaje;
        this.subtotalBase = subtotalBase;
        this.subtotalIva = subtotalIva;
        this.subtotalRetencion = subtotalRetencion;
        this.subtotalFinal = subtotalFinal;
    }



}
