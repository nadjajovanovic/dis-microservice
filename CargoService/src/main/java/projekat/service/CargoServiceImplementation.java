package projekat.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import projekat.dto.CargoRequest;
import projekat.dto.CargoResponse;
import projekat.entity.Cargo;
import projekat.exception.CustomException;
import projekat.repository.CargoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CargoServiceImplementation implements CargoService{

    @Autowired
    private CargoRepository repository;

    @Override
    public Cargo addCargo(CargoRequest cargoRequest) {
        log.info("You are adding a cargo");

        Cargo cargo = Cargo.builder()
                .mimeType(cargoRequest.getMimeType())
                .packing(cargoRequest.getPacking())
                .description(cargoRequest.getDescription())
                .build();

        repository.save(cargo);
        log.info("Cargo created");
        return cargo;
    }

    @Override
    public Cargo getCargoById(int id) {
        log.info("Getting cargo with id: {}", id);

        final var cargo = repository.findById(id)
                .orElseThrow(() -> new CustomException("Given id is no found", HttpStatus.NOT_FOUND));
        return cargo;
    }

    @Override
    public Collection<Cargo> getAllCargos() {
        final var cargos = repository.findAll();
        return cargos;
    }
}
