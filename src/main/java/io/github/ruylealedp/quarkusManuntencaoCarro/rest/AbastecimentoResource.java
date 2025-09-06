package io.github.ruylealedp.quarkusManuntencaoCarro.rest;

import io.github.ruylealedp.quarkusManuntencaoCarro.domain.model.Abastecimento;
import io.github.ruylealedp.quarkusManuntencaoCarro.domain.model.domain.repositorio.AbastecimentoRepository;
import io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto.CreateAbastecimentoRequest;
import io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto.ResponseError;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Set;

@Path("/abastecimento")
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class AbastecimentoResource {

    private AbastecimentoRepository repositorio;
    private final Validator validator;

    @Inject
    public AbastecimentoResource(AbastecimentoRepository repositorio, Validator validator){
        this.repositorio = repositorio;
        this.validator = validator;
    }
    @POST
    public Response createAbastecimento(CreateAbastecimentoRequest abastecimentoRequest) {
        Set<ConstraintViolation<CreateAbastecimentoRequest>> violations = validator.validate(abastecimentoRequest);
        if(!violations.isEmpty()){
            ResponseError responseError = ResponseError.createFromValidation(violations);
            return Response.status(400).entity(responseError).build();
        }
        Abastecimento abastecimento = new Abastecimento();
        abastecimento.setNomePosto(abastecimentoRequest.getNomePosto());
        abastecimento.setLitros(abastecimentoRequest.getLitros());
        abastecimento.setValorGasto(abastecimentoRequest.getValorGasto());
        abastecimento.setKmPercorrido(abastecimentoRequest.getKmPercorrido());
        repositorio.persist(abastecimento);
        return Response.ok(abastecimento).build();
    }

    @GET

    public Response ListAllAbastecimento() {
        PanacheQuery<PanacheEntityBase> query = Abastecimento.findAll();

        return Response.ok(query.list()).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")

    public Response deleteAbastecimento(@PathParam("id") Long id) {
        Abastecimento abastecimento = Abastecimento.findById(id);

        if (abastecimento != null) {
            abastecimento.delete();
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Transactional
    @Path("{id}")

    public Response updateAbastecimento(@PathParam("id") Long id, CreateAbastecimentoRequest AbastecimentoData) {
        Abastecimento abastecimento = Abastecimento.findById(id);
        if (abastecimento != null) {
        abastecimento.setNomePosto(AbastecimentoData.getNomePosto());
        abastecimento.setLitros(AbastecimentoData.getLitros());
        abastecimento.setValorGasto(AbastecimentoData.getValorGasto());
        abastecimento.setKmPercorrido(AbastecimentoData.getKmPercorrido());
        return Response.ok(abastecimento).build();


    }
      return Response.status(Response.Status.NOT_FOUND).build();

}
}
