package org.denso.skateboarddataaccessor.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Skateboard {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Owner owner;

    @NotBlank
    private String brand;

    @NonNull
    private Double weight;

    @NonNull
    private Double height;

    @NonNull
    private Double length;

    @NonNull
    private boolean available;

    @UpdateTimestamp
    private Date lastUpdated;
}
