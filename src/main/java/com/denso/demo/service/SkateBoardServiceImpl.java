package com.denso.demo.service;

import com.denso.demo.domain.SkateBoard;
import com.denso.demo.entity.SkateBoardEntity;
import com.denso.demo.repository.SkateBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.of;

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
                        .ownerName(entity.getName())
                        .description(entity.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void addSkateBoard(SkateBoard skateBoard) {

        of(skateBoard)
                .map(board -> SkateBoardEntity.builder()
                        .name(skateBoard.getOwnerName())
                        .description(skateBoard.getDescription())
                        .build())
                .ifPresent(skateBoardRepository::save);

    }

    @Override
    public void updateSkateBoardAvailability(String name, boolean availability) {

        skateBoardRepository.findAllByName(name)
                .findFirst()
                .ifPresent(entity -> {
                    entity.setAvailable(availability);
                    skateBoardRepository.save(entity);
                });
    }
}
