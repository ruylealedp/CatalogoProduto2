package io.github.ruylealedp.quarkusManuntencaoCarro.domain.model.domain.repositorio;

import io.github.ruylealedp.quarkusManuntencaoCarro.domain.model.Abastecimento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AbastecimentoRepository implements PanacheRepository<Abastecimento> {
}
