package org.denso.skateboarddataaccessor.model.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FindBySpecsFilterRequest {
    private String brand;
    private Double minLength;
    private Double maxLength;
    private Double minHeight;
    private Double maxHeight;
    private Double minWidth;
    private Double maxWidth;
    private Double minWeight;
    private Double maxWeight;
}
