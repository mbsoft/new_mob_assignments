package com.denso.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkateBoard {

    private long id;
    private String ownerName;
    private String description;
    private String brand;
    private BigDecimal weight;
    private BigDecimal length;
    private String location;
    private String timeStamp;
    private boolean available;
}
