package br.com.ungaratto93.fx.domain.rate;

import br.com.ungaratto93.fx.domain.fiat.Symbol;

public record RateOutPut(
        Symbol source, Symbol target, double value, String time
) { }
