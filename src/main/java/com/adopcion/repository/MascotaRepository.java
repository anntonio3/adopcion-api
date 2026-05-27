package com.adopcion.repository;

import com.adopcion.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
    List<Mascota> findByActivoTrue();
    List<Mascota> findByUsuarioDonador_IdUsuario(Integer idUsuario);
    List<Mascota> findByEstadoAdopcion(Mascota.EstadoAdopcion estado);
    List<Mascota> findByTipoMascota_IdTipoMascota(Integer idTipo);
}
