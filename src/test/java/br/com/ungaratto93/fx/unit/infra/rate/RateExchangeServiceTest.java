package br.com.ungaratto93.fx.unit.infra.rate;

import br.com.ungaratto93.fx.unit.infra.rate.mock.WiseMockResponse;
import br.com.ungaratto93.fx.domain.fiat.Symbol;
import br.com.ungaratto93.fx.domain.rate.*;
import br.com.ungaratto93.fx.infra.rate.WiseRateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class RateExchangeServiceTest {

    @InjectMocks
    WiseRateService wiseRateService;

    @Mock
    FeignWiseClient client;

    @Test
    void shouldReturnCurrentRatesFromWiseWhenGetWithValidPath() throws WiseRateServiceException {

        List<Rate> response = WiseMockResponse.get200Response();

        Mockito.when(client.getRates(
                "USD",
                "BRL",
                1,
                "daily",
                "day" )
        ).thenReturn(response);



        RateOutPut rates = wiseRateService.getRates(
                new RateInput(
                        Symbol.USD,
                        Symbol.BRL,
                        1,
                        String.valueOf(System.currentTimeMillis())
                )
        );



        Assertions.assertInstanceOf(RateOutPut.class, rates);
        Assertions.assertEquals("USD", rates.source().name());
        Assertions.assertEquals("BRL", rates.target().name());
        Assertions.assertEquals(4.9379, rates.value());
        Assertions.assertEquals("1702749167489", rates.time());

    }


}
