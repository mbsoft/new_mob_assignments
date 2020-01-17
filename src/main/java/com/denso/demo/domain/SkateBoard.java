package com.denso.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkateBoard {

    private String ownerName;
    private String description;
    private String brand;
    private BigDecimal weight;
    private BigDecimal length;
    private String location;
    private LocalDateTime timestamp;
}
