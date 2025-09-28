package io.github.ruylealedp.api;

import jakarta.json.bind.JsonbException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class JsonbExceptionMapper implements ExceptionMapper<JsonbException> {

    @Override
    public Response toResponse(JsonbException exception) {
        // Loga a exceção para que ela possa ser investigada no servidor.
        System.err.println("Erro de parseamento JSON: " + exception.getMessage());
        exception.printStackTrace();

        // resposta com status 400
        // .
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("Todos os campos devem ser preenchidos..")
                .build();
    }
}
