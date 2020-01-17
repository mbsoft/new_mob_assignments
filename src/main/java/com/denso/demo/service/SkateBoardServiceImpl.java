package com.denso.demo.service;

import com.denso.demo.domain.SkateBoard;
import com.denso.demo.entity.SkateBoardEntity;
import com.denso.demo.repository.SkateBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class SkateBoardServiceImpl implements SkateBoardService {

    private final SkateBoardRepository skateBoardRepository;

    @Override
    public List<SkateBoard> getAllAvailableSkateBoards() {

        return skateBoardRepository.findAll()
                .stream()
                .filter(SkateBoardEntity::isAvailable)
                .map(entity -> SkateBoard.builder()
                        .ownerName(entity.getOwnerName())
                        .description(entity.getDescription())
                        .id(entity.getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void addSkateBoard(SkateBoard skateBoard) {

        ofNullable(skateBoard)
                .map(board -> SkateBoardEntity.builder()
                        .ownerName(skateBoard.getOwnerName())
                        .description(skateBoard.getDescription())
                        .available(skateBoard.isAvailable())
                        .brand(skateBoard.getBrand())
                        .length(skateBoard.getLength())
                        .weight(skateBoard.getWeight())
                        .location(skateBoard.getLocation())
                        .build())
                .ifPresent(skateBoardRepository::save);

    }

    @Override
    public void updateSkateBoard(long id, SkateBoard skateBoard) {

        skateBoardRepository.findById(id)
                .map(entity -> {
                    entity.setOwnerName(skateBoard.getOwnerName());
                    entity.setDescription(skateBoard.getDescription());
                    entity.setAvailable(skateBoard.isAvailable());
                    entity.setBrand(skateBoard.getBrand());
                    entity.setLength(skateBoard.getLength());
                    entity.setLocation(skateBoard.getLocation());
                    entity.setWeight(skateBoard.getWeight());
                    return entity;
                })
                .ifPresent(skateBoardRepository::save);
    }

    @Override
    public void deleteSkateBoard(long skateBoardId) {

        skateBoardRepository.deleteById(skateBoardId);
    }

    @Override
    public SkateBoard getSkateBoard(long skateBoardId) {
        return skateBoardRepository.findById(skateBoardId)
                .map(skateBoard -> SkateBoard.builder()
                        .ownerName(skateBoard.getOwnerName())
                        .description(skateBoard.getDescription())
                        .available(skateBoard.isAvailable())
                        .brand(skateBoard.getBrand())
                        .length(skateBoard.getLength())
                        .weight(skateBoard.getWeight())
                        .location(skateBoard.getLocation())
                        .build())
                .orElse(null);
    }
}
