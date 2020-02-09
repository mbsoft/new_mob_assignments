package org.denso.skateboarddataaccessor.service;

import lombok.extern.slf4j.Slf4j;
import org.denso.skateboarddataaccessor.exception.OwnerNotFoundException;
import org.denso.skateboarddataaccessor.model.Owner;
import org.denso.skateboarddataaccessor.model.Skateboard;
import org.denso.skateboarddataaccessor.model.request.AddBoardToOwnerRequest;
import org.denso.skateboarddataaccessor.model.request.UpdateOwnerBoardsRequest;
import org.denso.skateboarddataaccessor.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class SkateboardOwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public SkateboardOwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public ResponseEntity<String> addOwner(Owner owner) {
        log.debug("Received request to save owner {} to the database", owner);
        Owner newOwner = ownerRepository.save(owner);
        if (newOwner == null) {
            return new ResponseEntity<>("Failed to save owner: " + owner.getName(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Successfully saved owner: " + owner.getName(), HttpStatus.OK);
    }

    public ResponseEntity<Owner> getOwnerById(Long id) throws OwnerNotFoundException {
        return new ResponseEntity<>(findOwnerById(id), HttpStatus.OK);
    }

    public ResponseEntity<List<Owner>> getOwnersByName(String ownerName) {
        List<Owner> owners = ownerRepository.findByNameEquals(ownerName);

        if (owners.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<String> deleteOwner(Long id) throws OwnerNotFoundException {
        Owner owner = findOwnerById(id);
        ownerRepository.delete(owner);
        return new ResponseEntity<>("Successfully deleted owner with id: " + id, HttpStatus.OK);
    }

    public ResponseEntity<String> updateOwnerBoards(UpdateOwnerBoardsRequest request) throws OwnerNotFoundException {
        Owner owner = findOwnerById(request.getOwnerId());
        owner.setBoards(request.getBoards());
        Owner savedOwner = ownerRepository.save(owner);

        if (savedOwner == null) {
            return new ResponseEntity<>("Trouble saving board to owner!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Successfully updates boards for owner id: " + request.getOwnerId(), HttpStatus.OK);
    }

    public ResponseEntity<String> updateOwnerName(Long id, String newName) throws OwnerNotFoundException {
        Owner owner = findOwnerById(id);
        owner.setName(newName);
        Owner savedOwner = ownerRepository.save(owner);

        if (savedOwner == null) {
            return new ResponseEntity<>("Trouble saving board to owner!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Successfully updated owner name to: " + newName, HttpStatus.OK);
    }

    public ResponseEntity<String> addBoardToOwner(AddBoardToOwnerRequest request) throws OwnerNotFoundException {

        Owner owner = findOwnerById(request.getOwnerId());
        Set<Skateboard> boards = owner.getBoards();
        boards.add(request.getSkateboard());
        owner.setBoards(boards);
        Owner savedOwner = ownerRepository.save(owner);

        if (savedOwner == null) {
            return new ResponseEntity<>("Trouble saving board to owner!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Successfully saved board for owner id: " + request.getOwnerId(), HttpStatus.OK);
    }

    private Owner findOwnerById(Long id) throws OwnerNotFoundException {
        Optional<Owner> owner = ownerRepository.findById(id);

        if (!owner.isPresent()) {
            throw new OwnerNotFoundException(id);
        }

        return owner.get();
    }
}
