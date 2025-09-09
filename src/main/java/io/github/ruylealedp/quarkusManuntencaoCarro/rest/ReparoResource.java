package io.github.ruylealedp.quarkusManuntencaoCarro.rest;

import io.github.ruylealedp.quarkusManuntencaoCarro.domain.model.Descricao;
import io.github.ruylealedp.quarkusManuntencaoCarro.domain.model.Reparo;
import io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto.CreateDescricaoRequest;
import io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto.CreateReparoRequest;
import io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto.ReparoResponse;
import io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto.ResponseError;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/Reparo")
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReparoResource {

    private final Validator validator;

    @Inject
    public ReparoResource(Validator validator){
        this.validator = validator;
    }

    @POST
    public Response createReparo(CreateReparoRequest reparoRequest) {
        Set<ConstraintViolation<CreateReparoRequest>> violations = validator.validate(reparoRequest);
        if (!violations.isEmpty()) {
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);
        }
        Reparo reparo = new Reparo();
        reparo.setMecanico(reparoRequest.getMecanico());
        reparo.setServico(reparoRequest.getServico());
        reparo.setValorServico(reparoRequest.getValorServico());
        reparo.setDataServico(reparoRequest.getDataServico());
        if (reparoRequest.getDescricoes() != null) {
            List<Descricao> descricoes = new ArrayList<>();
            for (CreateDescricaoRequest descricaoRequest : reparoRequest.getDescricoes()) {
                Descricao descricao = new Descricao();
                descricao.setText(descricaoRequest.getText());
                descricao.setDataCriacao(LocalDateTime.now());
                descricao.setReparo(reparo);
                descricoes.add(descricao);
            }
            reparo.setDescricoes(descricoes);
        }
        reparo.persist();
        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(ReparoResponse.fromEntity(reparo))
                .build();
    }

    @GET
    public Response listAllReparo(){
        List<Reparo> reparos = Reparo.listAll();
        List<ReparoResponse> responseList = reparos.stream()
                .map(ReparoResponse::fromEntity)
                .collect(Collectors.toList());
        return Response.ok(responseList).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteReparo(@PathParam("id") Long id) {
        Reparo reparo = Reparo.findById(id);
        if (reparo != null) {
            reparo.delete();
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("{id}")
    public Response updateReparo(@PathParam("id") Long id, CreateReparoRequest reparoData){
        Reparo reparo = Reparo.findById(id);
        if (reparo != null){
            reparo.setMecanico(reparoData.getMecanico());
            reparo.setServico(reparoData.getServico());
            reparo.setValorServico(reparoData.getValorServico());
            reparo.setDataServico(reparoData.getDataServico());
            if (reparoData.getDescricoes() != null) {
                reparo.getDescricoes().clear();
                for (CreateDescricaoRequest descricaoRequest : reparoData.getDescricoes()) {
                    Descricao descricao = new Descricao();
                    descricao.setText(descricaoRequest.getText());
                    descricao.setDataCriacao(LocalDateTime.now());
                    descricao.setReparo(reparo);
                    reparo.getDescricoes().add(descricao);
                }
            }
            reparo.persist();
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
