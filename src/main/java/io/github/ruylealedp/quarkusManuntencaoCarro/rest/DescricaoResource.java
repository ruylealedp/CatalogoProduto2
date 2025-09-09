package io.github.ruylealedp.quarkusManuntencaoCarro.rest;

import io.github.ruylealedp.quarkusManuntencaoCarro.domain.model.Descricao;
import io.github.ruylealedp.quarkusManuntencaoCarro.domain.model.Reparo;
import io.github.ruylealedp.quarkusManuntencaoCarro.domain.model.domain.repositorio.DescricaoRepository;
import io.github.ruylealedp.quarkusManuntencaoCarro.domain.model.domain.repositorio.ReparoRepository;
import io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto.CreateDescricaoRequest;
import io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto.DescricaoResponse;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Path("/Reparo/{id}/descricao")
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DescricaoResource {

    private ReparoRepository reparoRepository;
    private final DescricaoRepository repository;

    @Inject
    public DescricaoResource(ReparoRepository reparoRepository, DescricaoRepository repository){
        this.reparoRepository = reparoRepository;
        this.repository = repository;
    }

    @POST
    @Transactional
    public Response saveDescricao(@PathParam("id") Long id, CreateDescricaoRequest request){
        Reparo reparo = reparoRepository.findById(id);
        if (reparo == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Descricao descricao = new Descricao();
        descricao.setText(request.getText());
        descricao.setReparo(reparo);
        descricao.setDataCriacao(LocalDateTime.now());

        repository.persist(descricao);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listDescricao(@PathParam("id") Long id){
        Reparo reparo = reparoRepository.findById(id);

        if (reparo == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PanacheQuery<Descricao> query = repository.find("reparo", Sort.by("dataCriacao", Sort.Direction.Descending), reparo);
        var list = query.list();


        var descricaoResponseList = list.stream().map(post -> DescricaoResponse.fromEntity(post)).collect(Collectors.toList());

        return Response.ok(descricaoResponseList).build();
    }
}
