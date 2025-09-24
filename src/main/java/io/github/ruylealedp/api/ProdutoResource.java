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

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    // Simulação de banco de dados em memória
    private static List<Produto> produtos = new ArrayList<>();


    @GET
    @RolesAllowed({"admin", "user"})
    public List<ProdutoResponseDTO> listarProdutos() {
        // Mapeia cada Produto para um ProdutoResponseDTO com a ordem especificada
        return produtos.stream()
                .map(produto -> new ProdutoResponseDTO(
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getDescricao(),
                        produto.getId() // Inclui o ID na resposta do DTO
                ))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin", "user"})
    public Response buscarProduto(@PathParam("id") String id) {
        Optional<Produto> produtoOptional = produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        // Se encontrado, retorna o DTO com a ordem e o ID
        return produtoOptional.map(produto -> Response.ok(new ProdutoResponseDTO(
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getDescricao(),
                        produto.getId()
                )))
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @RolesAllowed("admin")
    public Response criarProduto(@Valid Produto produto) {
        // O ID é gerado automaticamente pelo construtor de Produto
        produtos.add(produto);
        // Retorna o produto criado (com o ID gerado e campos na ordem correta)
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
    public Response atualizarProduto(@PathParam("id") String id, @Valid Produto produtoAtualizado) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId().equals(id)) {
                // Garante que o ID não mude e mantém o ID original do produto
                produtoAtualizado.setId(id);
                produtos.set(i, produtoAtualizado);
                // Retorna o produto atualizado (com o ID original e campos na ordem correta)
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
    public Response excluirProduto(@PathParam("id") String id) {
        boolean removido = produtos.removeIf(p -> p.getId().equals(id));
        if (removido) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}