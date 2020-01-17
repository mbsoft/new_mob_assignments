package com.denso.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SkateBoardEntity {

    @Id
    private long id;
    private String ownerName;
    private String description;
    private String brand;
    private BigDecimal weight;
    private BigDecimal length;
    private String location;
    private LocalDateTime timestamp;
    private boolean available;
}
