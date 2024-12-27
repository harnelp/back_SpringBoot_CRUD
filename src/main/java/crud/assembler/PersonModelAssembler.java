package crud.assembler;

import crud.controller.PersonController;
import crud.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Ensamblador para construir representaciones HATEOAS de la entidad Person.
 */
@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {

    @Override
    public EntityModel<Person> toModel(Person person) {
        return EntityModel.of(person,
                linkTo(methodOn(PersonController.class).getPersonById(person.getId())).withSelfRel(),
                linkTo(methodOn(PersonController.class).getAllPersonsPaged(0, 10)).withRel("all-persons"));
    }

    /**
     * Convierte un Page<Person> en un PagedModel<EntityModel<Person>> con enlaces HATEOAS.
     *
     * @param personPage Página de personas.
     * @return Un modelo paginado con enlaces HATEOAS.
     */
    public PagedModel<EntityModel<Person>> toPagedModel(Page<Person> personPage) {
        return PagedModel.of(
                personPage.stream().map(this::toModel).collect(Collectors.toList()),
                new PagedModel.PageMetadata(
                        personPage.getSize(),
                        personPage.getNumber(),
                        personPage.getTotalElements(),
                        personPage.getTotalPages()
                ),
                linkTo(methodOn(PersonController.class)
                        .getAllPersonsPaged(personPage.getNumber(), personPage.getSize()))
                        .withSelfRel()
        );
    }
}
