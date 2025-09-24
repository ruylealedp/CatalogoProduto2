package io.github.ruylealedp.api;

import jakarta.json.bind.JsonbException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * Este provedor de JAX-RS mapeia exceções de parseamento JSON
 * (JsonbException) para uma resposta HTTP 400 Bad Request.
 * Isso garante que o cliente receba uma mensagem de erro clara
 * quando a requisição contiver um JSON malformado.
 */
@Provider
public class JsonbExceptionMapper implements ExceptionMapper<JsonbException> {

    @Override
    public Response toResponse(JsonbException exception) {
        // Loga a exceção para que ela possa ser investigada no servidor.
        System.err.println("Erro de parseamento JSON: " + exception.getMessage());
        exception.printStackTrace();

        // Constrói uma resposta com status 400 e uma mensagem amigável.
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("Todos os campos devem ser preenchidos..")
                .build();
    }
}
