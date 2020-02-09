package com.detroitlabs.skateboard.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@ApiModel(description = "All details about SkateBoard")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SkateBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, message = "Owner Name should have atleast 2 characters")
    @ApiModelProperty(notes = "Owner should have atleast 2 characters")
    private String ownerName;

    @Size(min = 2, message = "Brand Name should have atleast 2 characters")
    @ApiModelProperty(notes = "Brand should have atleast 2 characters")
    private String brand;

    @ApiModelProperty(notes = "Weight should be atleast 50 lb")
    private int weight;

    @ApiModelProperty(notes = "Length should be atleast 15 cm")
    private int length;

    @ApiModelProperty(notes = "Skateboard is available or not")
    private boolean isBoardAvailable;
}