package com.skateboardshare.skateboard_Rest_API.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@ApiModel(description = "All details about SkateBoard")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Skateboard {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 3, message = "Board Owner should have a name of minimum 3 charecters")
    @ApiModelProperty(notes = "Board owners name should contain atleast 3 charecters")
    private String ownerName;

    @Size(min = 3, message = "Board names brand should have a min of 3 charecters")
    @ApiModelProperty(notes = "Board brand names should have a min of 3 charecters")
    private String brand;

    @ApiModelProperty(notes = "Weight of the board")
    private int weight;

    @ApiModelProperty(notes = "Length of the board")
    private int length;

    @ApiModelProperty(notes = "Location to pickup/dropoff the board from")
    private String location;

    @ApiModelProperty(notes = "Borrower value can be null/updated accordingly")
    private String borrowerName;

    @ApiModelProperty(notes = "Availability of skateboard")
    private boolean isBoardAvailable;
}
