package br.com.ungaratto93.fx.ui;

import br.com.ungaratto93.fx.domain.fiat.FiatInputData;
import br.com.ungaratto93.fx.domain.fiat.FiatOutputData;
import br.com.ungaratto93.fx.domain.rate.WiseRateServiceException;
import br.com.ungaratto93.fx.usecase.fiat.exchange.FiatExcxhangeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("fx/v1")
public class FiatExchangeController {

    @Autowired
    private FiatExcxhangeUseCase fiatExchangeUsecase;

    @Description("Retorna o valor obtido apos o calculo de cambio")
    @PostMapping("/exchanges")
    public ResponseEntity<FiatOutputData> get(@RequestBody FiatInputData fiatInputData) throws WiseRateServiceException {
       var data = fiatExchangeUsecase.exec(fiatInputData);
       return ResponseEntity.ok(data);
    }
}
