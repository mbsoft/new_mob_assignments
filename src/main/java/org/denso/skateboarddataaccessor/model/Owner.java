package org.denso.skateboarddataaccessor.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Owner {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    private String name;

    @OneToMany(cascade = {CascadeType.ALL})
    @JsonBackReference
    @JoinColumn(name = "owner_id")
    private Set<Skateboard> boards;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date lastUpdated;
}
