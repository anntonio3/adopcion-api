package com.adopcion.service.impl;

import com.adopcion.exception.ResourceNotFoundException;
import com.adopcion.model.Mascota;
import com.adopcion.repository.*;
import com.adopcion.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CatTipoMascotaRepository catTipoMascotaRepository;

    @Override
    public List<Mascota> findAll() {
        return mascotaRepository.findByActivoTrue();
    }

    @Override
    public List<Mascota> findDisponibles() {
        return mascotaRepository.findByEstadoAdopcion(Mascota.EstadoAdopcion.Disponible);
    }

    @Override
    public Mascota findById(Integer id) {
        return mascotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota", id));
    }

    @Override
    public Mascota create(Integer idDonador, Integer idTipo, Mascota mascota) {
        mascota.setUsuarioDonador(
                usuarioRepository.findById(idDonador)
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario", idDonador))
        );
        mascota.setTipoMascota(
                catTipoMascotaRepository.findById(idTipo)
                        .orElseThrow(() -> new ResourceNotFoundException("CatTipoMascota", idTipo))
        );
        return mascotaRepository.save(mascota);
    }

    @Override
    public Mascota update(Integer id, Integer idTipo, Mascota data) {
        Mascota mascota = findById(id);
        mascota.setNombre(data.getNombre());
        mascota.setRaza(data.getRaza());
        mascota.setSexo(data.getSexo());
        mascota.setEdadAproximada(data.getEdadAproximada());
        mascota.setDescripcion(data.getDescripcion());
        mascota.setEstadoAdopcion(data.getEstadoAdopcion());
        if (idTipo != null) {
            mascota.setTipoMascota(
                    catTipoMascotaRepository.findById(idTipo)
                            .orElseThrow(() -> new ResourceNotFoundException("CatTipoMascota", idTipo))
            );
        }
        return mascotaRepository.save(mascota);
    }

    @Override
    public void delete(Integer id) {
        Mascota mascota = findById(id);
        mascota.setActivo(false);
        mascotaRepository.save(mascota);
    }
}
