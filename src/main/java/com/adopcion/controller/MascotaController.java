package com.adopcion.controller;

import com.adopcion.dto.MascotaResponseDTO;
import com.adopcion.mapper.MascotaMapper;
import com.adopcion.model.Mascota;
import com.adopcion.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/disponibles")
    public ResponseEntity<List<Mascota>> getDisponibles() {
        return ResponseEntity.ok(mascotaService.findDisponibles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(mascotaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MascotaResponseDTO> create(
            @RequestParam Integer idDonador,
            @RequestParam Integer idTipo,
            @RequestBody Mascota mascota) {
        Mascota nueva = mascotaService.create(idDonador, idTipo, mascota);
        return ResponseEntity.status(HttpStatus.CREATED).body(MascotaMapper.toDTO(nueva));
    }

    @GetMapping
    public ResponseEntity<List<MascotaResponseDTO>> getAll() {
        return ResponseEntity.ok(
                mascotaService.findAll().stream().map(MascotaMapper::toDTO).toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> update(
            @PathVariable Integer id,
            @RequestParam(required = false) Integer idTipo,
            @RequestBody Mascota mascota) {
        return ResponseEntity.ok(mascotaService.update(id, idTipo, mascota));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        mascotaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
