package br.com.ungaratto93.fx.domain.fiat;

import br.com.ungaratto93.fx.domain.rate.RateInput;

public interface FiatExchangeService {

     FiatOutputData exchangeFiatWith(RateInput rate, FiatInputData it);

}
