package projekat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cargoId;

    @Column(name="mimeType")
    private String mimeType;

    @Column(name="packing")
    private String packing;

    @Column(name="description")
    private String description;
}
