package org.denso.skateboarddataaccessor.model.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.denso.skateboarddataaccessor.model.Skateboard;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
public class UpdateOwnerBoardsRequest {

    @NonNull
    private Long ownerId;

    @NotEmpty
    private Set<Skateboard> boards;
}
