package br.com.ungaratto93.fx.unit.domain.rate;

import br.com.ungaratto93.fx.domain.fiat.Symbol;
import br.com.ungaratto93.fx.domain.rate.Rate;
import br.com.ungaratto93.fx.domain.rate.RateCache;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class RateCacheTest {

    @Test
    public void deveRetornarUmObjRateBuscandoPelaKeyNoCache() {

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
                rateCache.getByKeyFromCache("BRL_USD").getSource()
        );
        Assertions.assertEquals(
                rate.getTarget(),
                rateCache.getByKeyFromCache("BRL_USD").getTarget()
        );
        Assertions.assertEquals(
                rate.getValue(),
                rateCache.getByKeyFromCache("BRL_USD").getValue()
        );
        Assertions.assertEquals(
                rate.getTime(),
                rateCache.getByKeyFromCache("BRL_USD").getTime()
        );
        Assertions.assertEquals(
                rate.getClass(),
                rateCache.getByKeyFromCache("BRL_USD").getClass()
        );

    }

    @Test
    public void deveRetornaUmObjRateBuscandoPelaKeyComDoisObjNoCache() {
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
        Rate rate = rateCache.getByKeyFromCache(key);

        Assertions.assertEquals(
                rate.getSource(),
                rateCache.getByKeyFromCache(key).getSource()
        );
        Assertions.assertEquals(
                rate.getTarget(),
                rateCache.getByKeyFromCache(key).getTarget()
        );
        Assertions.assertEquals(
                rate.getValue(),
                rateCache.getByKeyFromCache(key).getValue()
        );
        Assertions.assertEquals(
                rate.getTime(),
                rateCache.getByKeyFromCache(key).getTime()
        );
        Assertions.assertEquals(
                rate.getClass(),
                rateCache.getByKeyFromCache(key).getClass()
        );
    }

    @Test
    public void deveRemoverTodosObjetosNoCache() {
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

        String keyUSD = "USD_BRL";
        String keyBRL = "BRL_USD";
        rateCache.removeByKeyFromCache(keyUSD);
        rateCache.removeByKeyFromCache(keyBRL);

        Assertions.assertTrue(rateCache.isEmpty());

    }

    @Test
    public void deveRetornarQueRateEAntiga() {



        var rateBrl = new Rate(
                Symbol.BRL,
                Symbol.USD,
                4.916,
                String.valueOf(System.currentTimeMillis() - 100));

        RateCache rateCache = new RateCache();
        rateCache.putRateOnCache(
                rateBrl.getSource(),
                rateBrl.getTarget(),
                rateBrl.getValue(),
                rateBrl.getTime());

        Assertions.assertTrue(rateCache.isRateOld("BRL_USD"));

    }
}
