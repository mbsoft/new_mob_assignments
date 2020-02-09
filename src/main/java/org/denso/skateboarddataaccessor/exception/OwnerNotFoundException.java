package org.denso.skateboarddataaccessor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OwnerNotFoundException extends Exception {

    public OwnerNotFoundException(Long id) {
        super("Failed to find owner with id: " + id);
    }

}
