package br.com.ungaratto93.fx.unit.infra.rate.mock;

import br.com.ungaratto93.fx.domain.fiat.Symbol;
import br.com.ungaratto93.fx.domain.rate.Rate;

public class RateMock {

    public static Rate getInstance() {
        return Rate.builder()
                .source(Symbol.USD)
                .target(Symbol.BRL)
                .value(4.9379)
                .time("1702749167489")
                .build();
    }
}
