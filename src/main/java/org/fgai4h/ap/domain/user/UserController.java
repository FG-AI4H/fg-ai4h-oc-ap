package org.fgai4h.ap.domain.user;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final AnnotatorRepository annotatorRepository;
    private final AnnotatorModelAssembler annotatorModelAssembler;

    public UserController(AnnotatorRepository annotatorRepository, AnnotatorModelAssembler annotatorModelAssembler){
        this.annotatorRepository = annotatorRepository;
        this.annotatorModelAssembler = annotatorModelAssembler;
    }

    @GetMapping("/annotators")
    public ResponseEntity<CollectionModel<AnnotatorModel>> getAllAnnotators()
    {
        List<AnnotatorEntity> annotatorEntities = annotatorRepository.findAll();
        return new ResponseEntity<>(
                annotatorModelAssembler.toCollectionModel(annotatorEntities),
                HttpStatus.OK);
    }
}