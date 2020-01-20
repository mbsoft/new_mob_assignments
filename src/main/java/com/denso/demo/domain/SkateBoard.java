package com.denso.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkateBoard {

    private long id;
    @NotBlank
    private String ownerName;
    private String description;
    private String brand;
    private BigDecimal weight;
    private BigDecimal length;
    private String location;
    private String timeStamp;
    private boolean available;
}
