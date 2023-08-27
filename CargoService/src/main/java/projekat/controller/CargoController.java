package projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekat.dto.CargoRequest;
import projekat.dto.CargoResponse;
import projekat.entity.Cargo;
import projekat.mapper.CargoMapper;
import projekat.service.CargoService;

import java.util.Collection;
import java.util.Comparator;

@RestController
@RequestMapping("/cargo")
public class CargoController {

    @Autowired
    private CargoService service;

    @PostMapping("/add")
    public ResponseEntity<Cargo> addCargo(@RequestBody CargoRequest request) {
        final var cargo = service.addCargo(request);
        return new ResponseEntity<>(cargo, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CargoResponse> getCargoById(@PathVariable Integer id) {
        final var cargo = service.getCargoById(id);
        final var response = CargoMapper.map(cargo);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping()
    public Collection<Cargo> getAll() {
        final var cargos = service.getAllCargos();
        final var listOfAllCargos = cargos
                .stream()
                .sorted(Comparator.comparingInt(Cargo::getCargoId))
                .toList();
        return listOfAllCargos;
    }
}
