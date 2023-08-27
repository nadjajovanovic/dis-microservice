package projekat.dto;

import lombok.Data;

@Data
public class CargoRequest {
    private String mimeType;
    private String packing;
    private String description;
}
