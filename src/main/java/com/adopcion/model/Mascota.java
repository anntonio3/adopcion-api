package com.adopcion.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Mascota")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMascota")
    private Integer idMascota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioDonador", nullable = false)
    private Usuario usuarioDonador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoMascota", nullable = false)
    private CatTipoMascota tipoMascota;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 100)
    private String raza;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('Macho','Hembra')")
    private Sexo sexo;

    @Column(length = 50)
    private String edadAproximada;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, columnDefinition = "ENUM('Disponible','En proceso','Adoptado')")
    private String estadoAdopcion = "Disponible";

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = false)
    private LocalDateTime fechaPublicacion;

    @PrePersist
    protected void onCreate() {
        if (fechaPublicacion == null) fechaPublicacion = LocalDateTime.now();
        if (estadoAdopcion == null) estadoAdopcion = "Disponible";
    }

    public enum Sexo { Macho, Hembra }

    // Constantes para evitar strings mágicos
    public static final String ESTADO_DISPONIBLE  = "Disponible";
    public static final String ESTADO_EN_PROCESO  = "En proceso";
    public static final String ESTADO_ADOPTADO    = "Adoptado";
}
