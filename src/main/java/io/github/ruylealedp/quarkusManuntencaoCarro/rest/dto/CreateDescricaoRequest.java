package io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class CreateDescricaoRequest {

    @NotBlank(message = "A descrição é obrigatória")
    private String Text;

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        this.Text = text;
    }
}
