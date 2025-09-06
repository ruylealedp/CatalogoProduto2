package io.github.ruylealedp.quarkusManuntencaoCarro.rest;

import io.github.ruylealedp.quarkusManuntencaoCarro.domain.model.Reparo;
import io.github.ruylealedp.quarkusManuntencaoCarro.domain.model.domain.repositorio.ReparoRepository;
import io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto.CreateReparoRequest;
import io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto.ResponseError;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Set;

@Path("/Reparo")
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ReparoResource {

    private ReparoRepository repositorio;
    private final Validator validator;

    @Inject
    public ReparoResource(ReparoRepository repositorio, Validator validator){
        this.repositorio = repositorio;
        this.validator = validator;
    }

    @POST
    public Response createReparo(CreateReparoRequest reparoRequest) {
        Set<ConstraintViolation<CreateReparoRequest>> violations = validator.validate(reparoRequest);
        if(!violations.isEmpty()){
            ResponseError responseError = ResponseError.createFromValidation(violations);
            return Response.status(400).entity(responseError).build();
        }

        Reparo reparo = new Reparo();
        reparo.setMecanico(reparoRequest.getMecanico());
        reparo.setServico(reparoRequest.getServico());
        reparo.setValorServico(reparoRequest.getValorServico());
        reparo.setDataServico(reparoRequest.getDataServico());
        repositorio.persist(reparo);
        return Response.ok(reparo).build();
    }

    @GET

    public Response listAllReparo(){
        PanacheQuery<Reparo> query = repositorio.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteReparo(@PathParam("id") Long id) {
        Reparo reparo = repositorio.findById(id);

        if (reparo != null) {
            repositorio.delete(reparo);

            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Transactional
    @Path("{id}")

    public Response updateReparo (@PathParam("id") Long id, CreateReparoRequest reparoData){
        Reparo reparo = repositorio.findById(id);
        if (reparo != null){
            reparo.setMecanico(reparoData.getMecanico());
            reparo.setServico(reparoData.getServico());
            reparo.setValorServico(reparoData.getValorServico());
            reparo.setDataServico(reparoData.getDataServico());
            repositorio.persist(reparo);
            return Response.ok(reparo).build();

        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

