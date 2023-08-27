package projekat.service;

import projekat.dto.CargoRequest;
import projekat.entity.Cargo;

import java.util.Collection;

public interface CargoService {

    Cargo addCargo(CargoRequest cargoRequest);
    Cargo getCargoById(int id);
    Collection<Cargo> getAllCargos();
}
