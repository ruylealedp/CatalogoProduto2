package io.github.ruylealedp.api;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import io.quarkus.cache.CacheResult;
import io.quarkus.cache.CacheInvalidate;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    // Simulação de banco de dados em memória
    private static List<Produto> produtos = new ArrayList<>();

    // 1. Estrutura Estática para rastrear a última operação de cache.
    private static class LastCacheOperation {
        // Status pode ser: "NENHUMA", "CACHE MISS", "CACHE INVALIDATE"
        public String status = "NENHUMA";
        public String targetId = "N/A";

        // Método para retornar o log em formato JSON
        public String toJson() {
            return String.format("{\"last_operation_status\": \"%s\", \"target_id\": \"%s\"}", status, targetId);
        }
    }

    // Instância estática para armazenar o estado global do cache
    private static LastCacheOperation cacheLog = new LastCacheOperation();


    @GET
    @RolesAllowed({"admin", "user"})
    public List<ProdutoResponseDTO> listarProdutos() {
        return produtos.stream()
                .map(produto -> new ProdutoResponseDTO(
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getDescricao(),
                        produto.getId()
                ))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin", "user"})
    @CacheResult(cacheName = "produto-cache")
    public Response buscarProduto(@PathParam("id") String id) {
        Optional<Produto> produtoOptional = produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        // 2. Se o método é executado (Cache Miss) e o produto é encontrado:
        // Registra CACHE MISS e o ID.
        return produtoOptional.map(produto -> {
                    // Este bloco SÓ é executado em caso de Cache MISS
                    cacheLog.status = "CACHE MISS";
                    cacheLog.targetId = id;

                    return Response.ok(new ProdutoResponseDTO(
                            produto.getNome(),
                            produto.getPreco(),
                            produto.getDescricao(),
                            produto.getId()
                    ));
                })
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @GET
    @Path("/cache/log")
    @RolesAllowed({"admin", "user"})
    public Response getCacheLog() {
        // 3. Retorna o JSON contendo o status e o ID da última operação
        return Response.ok(cacheLog.toJson()).build();
    }

    @POST
    @RolesAllowed("admin")
    public Response criarProduto(@Valid Produto produto) {
        produtos.add(produto);
        return Response.status(Response.Status.CREATED).entity(new ProdutoResponseDTO(
                produto.getNome(),
                produto.getPreco(),
                produto.getDescricao(),
                produto.getId()
        )).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    // Invalida o cache do produto atualizado
    @CacheInvalidate(cacheName = "produto-cache")
    public Response atualizarProduto(@PathParam("id") String id, @Valid Produto produtoAtualizado) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId().equals(id)) {
                produtoAtualizado.setId(id);
                produtos.set(i, produtoAtualizado);

                // 4. Se a atualização for bem-sucedida, registra CACHE INVALIDATE
                cacheLog.status = "CACHE INVALIDATE";
                cacheLog.targetId = id;

                return Response.ok(new ProdutoResponseDTO(
                        produtoAtualizado.getNome(),
                        produtoAtualizado.getPreco(),
                        produtoAtualizado.getDescricao(),
                        produtoAtualizado.getId()
                )).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    // Invalida o cache do produto removido
    @CacheInvalidate(cacheName = "produto-cache")
    public Response excluirProduto(@PathParam("id") String id) {
        boolean removido = produtos.removeIf(p -> p.getId().equals(id));
        if (removido) {
            // 5. Se a remoção for bem-sucedida, registra CACHE INVALIDATE
            cacheLog.status = "CACHE INVALIDATE";
            cacheLog.targetId = id;

            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
