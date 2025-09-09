package io.github.ruylealedp.quarkusManuntencaoCarro.rest.dto;

import io.github.ruylealedp.quarkusManuntencaoCarro.domain.model.Reparo;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReparoResponse {

    private Long id;
    private String mecanico;
    private String servico;
    private BigDecimal valorServico;
    private LocalDate dataServico;
    private List<DescricaoResponse> descricoes;

    public static ReparoResponse fromEntity(Reparo reparo) {
        ReparoResponse dto = new ReparoResponse();
        dto.setId(reparo.getId());
        dto.setMecanico(reparo.getMecanico());
        dto.setServico(reparo.getServico());
        dto.setValorServico(reparo.getValorServico());
        dto.setDataServico(reparo.getDataServico());
        if (reparo.getDescricoes() != null) {
            dto.setDescricoes(reparo.getDescricoes().stream()
                    .map(DescricaoResponse::fromEntity)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMecanico() { return mecanico; }
    public void setMecanico(String mecanico) { this.mecanico = mecanico; }
    public String getServico() { return servico; }
    public void setServico(String servico) { this.servico = servico; }
    public BigDecimal getValorServico() { return valorServico; }
    public void setValorServico(BigDecimal valorServico) { this.valorServico = valorServico; }
    public LocalDate getDataServico() { return dataServico; }
    public void setDataServico(LocalDate dataServico) { this.dataServico = dataServico; }
    public List<DescricaoResponse> getDescricoes() { return descricoes; }
    public void setDescricoes(List<DescricaoResponse> descricoes) { this.descricoes = descricoes; }
}
