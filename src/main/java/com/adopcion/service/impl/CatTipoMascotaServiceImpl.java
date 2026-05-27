package com.adopcion.service.impl;

import com.adopcion.exception.ResourceNotFoundException;
import com.adopcion.model.CatTipoMascota;
import com.adopcion.repository.CatTipoMascotaRepository;
import com.adopcion.service.CatTipoMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CatTipoMascotaServiceImpl implements CatTipoMascotaService {

    @Autowired
    private CatTipoMascotaRepository catTipoMascotaRepository;

    @Override
    public List<CatTipoMascota> findAll() {
        return catTipoMascotaRepository.findByActivoTrue();
    }

    @Override
    public CatTipoMascota findById(Integer id) {
        return catTipoMascotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CatTipoMascota", id));
    }

    @Override
    public CatTipoMascota create(CatTipoMascota tipo) {
        return catTipoMascotaRepository.save(tipo);
    }

    @Override
    public CatTipoMascota update(Integer id, CatTipoMascota data) {
        CatTipoMascota tipo = findById(id);
        tipo.setDescripcion(data.getDescripcion());
        tipo.setActivo(data.getActivo());
        return catTipoMascotaRepository.save(tipo);
    }

    @Override
    public void delete(Integer id) {
        CatTipoMascota tipo = findById(id);
        tipo.setActivo(false);
        catTipoMascotaRepository.save(tipo);
    }
}
