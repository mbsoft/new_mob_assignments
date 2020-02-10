package com.detroitlabs.skateboard.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@ApiModel(description = "All details about SkateBoard")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class SkateBoard {

    private Integer id;

    @Size(min = 2, message = "Owner Name should have at least 2 characters")
    @ApiModelProperty(notes = "Owner should have at least 2 characters")
    private String ownerName;

    @Size(min = 2, message = "Brand Name should have a t east 2 characters")
    @ApiModelProperty(notes = "Brand should have at least 2 characters")
    private String brand;

    @ApiModelProperty(notes = "Weight should be at least 50 lb")
    private int weight;

    @ApiModelProperty(notes = "Length should be at least 15 cm")
    private int length;

    @ApiModelProperty(notes = "Skateboard is available or not")
    private boolean isBoardAvailable;
}