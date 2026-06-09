package main.java.com.inventario.ecogarzones.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.inventario.ecogarzones.model.TipoInsumoEnum;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insumo")
    private Long idInsumo;

    @Column(name = "nombre_insumo", nullable = false)
    private String nombreInsumo;

    @Enumerated(EnumType.STRING)            //Clase Enum TipoInsumoEnum.java
    @Column(name = "tipo_insumo", nullable = false)
    private TipoInsumoEnum tipoInsumo; 

    @Column(name = "cantidad_insumo", nullable = false)
    private Integer cantidadInsumo;

    @Enumerated(EnumType.STRING)            //Clase Enum UnidadMedidaEnum.java
    @Column(name = "unidad_medida", nullable = false)
    private UnidadMedida unidadMedida;

    @Column(name = "precio_costo_unitario", precision = 10, scale = 2)
    private BigDecimal precioCostoUnitario;

    @Enumerated(EnumType.STRING)            //Clase Enum EstadoInsumoEnum.java
    @Column(name = "estado_insumos", nullable = false)
    private EstadoInsumo estadoInsumo; 
    
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}