package org.denso.skateboarddataaccessor.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;
import org.denso.skateboarddataaccessor.model.Skateboard;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddBoardToOwnerRequest {

    @NonNull
    private Long ownerId;

    @NonNull
    private Skateboard skateboard;
}
