package org.fgai4h.ap.domain.user.mapper;

import org.fgai4h.ap.domain.user.controller.UserController;
import org.fgai4h.ap.domain.user.entity.AnnotatorEntity;
import org.fgai4h.ap.domain.user.model.AnnotatorModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AnnotatorModelAssembler extends RepresentationModelAssemblerSupport<AnnotatorEntity, AnnotatorModel> {

    public AnnotatorModelAssembler() {
        super(UserController.class, AnnotatorModel.class);
    }

    @Override
    public AnnotatorModel toModel(AnnotatorEntity entity) {
        if(isNull(entity)){
            return null;
        }

        AnnotatorModel annotatorModel = instantiateModel(entity);

        annotatorModel.add(linkTo(
                methodOn(UserController.class)
                        .getAnnotatorById(entity.getAnnotatorUUID()))
                .withSelfRel());

        annotatorModel.setAnnotatorUUID(entity.getAnnotatorUUID());
        annotatorModel.setExpectedSalary(entity.getExpectedSalary());
        annotatorModel.setSelfAssessment(entity.getSelfAssessment());

        return annotatorModel;
    }

}
