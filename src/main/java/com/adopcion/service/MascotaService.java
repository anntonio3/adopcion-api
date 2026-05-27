package com.adopcion.service;

import com.adopcion.model.Mascota;
import java.util.List;

public interface MascotaService {
    List<Mascota> findAll();
    List<Mascota> findDisponibles();
    Mascota findById(Integer id);
    Mascota create(Integer idDonador, Integer idTipo, Mascota mascota);
    Mascota update(Integer id, Integer idTipo, Mascota mascota);
    void delete(Integer id);
}
