package org.denso.skateboarddataaccessor.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.denso.skateboarddataaccessor.model.Skateboard;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
public class UpdateOwnerBoardsRequest {

    @NonNull
    private Long ownerId;

    @NotEmpty
    private Set<Skateboard> boards;
}
