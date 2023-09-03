package projekat.external.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CargoResponse {

    private int cargoId;
    private String mimeType;
    private String packing;
    private String description;
}
