package projekat.mapper;

import projekat.dto.CargoResponse;
import projekat.entity.Cargo;

public class CargoMapper {

    public static CargoResponse map (Cargo cargo) {
        CargoResponse response = new CargoResponse();
        response.setCargoId(cargo.getCargoId());
        response.setMimeType(cargo.getMimeType());
        response.setPacking(cargo.getPacking());
        response.setDescription(cargo.getDescription());

        return response;
    }
}
