package br.com.ungaratto93.fx.unit.domain.rate;

import br.com.ungaratto93.fx.domain.fiat.Symbol;
import br.com.ungaratto93.fx.domain.rate.Rate;
import br.com.ungaratto93.fx.domain.rate.RateCache;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class RateCacheTest {

    @Test
    public void deveRetornarUmObjetoRateCache() {

        var rate = new Rate(
                Symbol.BRL,
                Symbol.USD,
                4.916,
                "1701993600000");

        RateCache rateCache = new RateCache();
        rateCache.putRateOnCache(
                rate.getSource(),
                rate.getTarget(),
                rate.getValue(),
                rate.getTime()
        );



        Assertions.assertEquals(
                rate.getSource(),
                rateCache.rateByKeyFromCache("BRL_USD").getSource()
        );
        Assertions.assertEquals(
                rate.getTarget(),
                rateCache.rateByKeyFromCache("BRL_USD").getTarget()
        );
        Assertions.assertEquals(
                rate.getValue(),
                rateCache.rateByKeyFromCache("BRL_USD").getValue()
        );
        Assertions.assertEquals(
                rate.getTime(),
                rateCache.rateByKeyFromCache("BRL_USD").getTime()
        );
        Assertions.assertEquals(
                rate.getClass(),
                rateCache.rateByKeyFromCache("BRL_USD").getClass()
        );

    }

    @Test
    public void deveRetornaUmObjetoRatePelaKeyNoCache() {
        var rateBrl = new Rate(
                Symbol.BRL,
                Symbol.USD,
                4.916,
                "1701993600000");
        var rateUsd = new Rate(
                Symbol.USD,
                Symbol.BRL,
                0.501,
                "1701993600000");

        RateCache rateCache = new RateCache();
        rateCache.putRateOnCache(
                rateBrl.getSource(),
                rateBrl.getTarget(),
                rateBrl.getValue(),
                rateBrl.getTime());
        rateCache.putRateOnCache(
                rateUsd.getSource(),
                rateUsd.getTarget(),
                rateUsd.getValue(),
                rateUsd.getTime());

        String key = "USD_BRL";
        Rate rate = rateCache.rateByKeyFromCache(key);

        Assertions.assertEquals(
                rate.getSource(),
                rateCache.rateByKeyFromCache(key).getSource()
        );
        Assertions.assertEquals(
                rate.getTarget(),
                rateCache.rateByKeyFromCache(key).getTarget()
        );
        Assertions.assertEquals(
                rate.getValue(),
                rateCache.rateByKeyFromCache(key).getValue()
        );
        Assertions.assertEquals(
                rate.getTime(),
                rateCache.rateByKeyFromCache(key).getTime()
        );
        Assertions.assertEquals(
                rate.getClass(),
                rateCache.rateByKeyFromCache(key).getClass()
        );
    }

}
