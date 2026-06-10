package com.inventario.ecogarzones.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.inventario.ecogarzones.model.TipoInsumo;

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
    private TipoInsumo tipoInsumo; 

    @Column(name = "cantidad_insumo", nullable = false)
    private Integer cantidadInsumo;

    @Enumerated(EnumType.STRING)            //Clase Enum UnidadMedidaEnum.java
    @Column(name = "unidad_medida", nullable = false)
    private UnidadMedidaEnum unidadMedida;

    @Column(name = "precio_costo_unitario", precision = 10, scale = 2)
    private BigDecimal precioCostoUnitario;

    @Enumerated(EnumType.STRING)            //Clase Enum EstadoInsumoEnum.java
    @Column(name = "estado_insumos", nullable = false)
    private EstadoInsumoEnum estadoInsumo; 
    
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "proveedor", length = 100)
    private String proveedor;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public Object getEncoding() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEncoding'");
    }
}