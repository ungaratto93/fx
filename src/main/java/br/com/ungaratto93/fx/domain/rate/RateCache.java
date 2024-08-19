package br.com.ungaratto93.fx.domain.rate;

import br.com.ungaratto93.fx.domain.fiat.Symbol;
import br.com.ungaratto93.fx.infra.fiat.FiatExchangeServiceWiseRates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@ToString
public class RateCache {

    private Map<String, Rate> rates = new HashMap<>();

    @Value("${cache.rate.limit.time.valid:900}")
    private long limitTimeForRateInCache;

    private static final Logger LOGGER = LoggerFactory.getLogger(FiatExchangeServiceWiseRates.class);

    public RateCache() {

    }

    public void putRateOnCache(Symbol source, Symbol target, double value, String time) {
        String keyName = generateKeyName(source, target);
        rates.put(
                keyName,
                Rate.builder()
                       .source(source)
                       .target(target)
                       .value(value)
                       .time(time)
                       .build()
       );
    }

    private String generateKeyName(Symbol source, Symbol target) {
        return source.name().concat("_").concat(target.name());
    }

    public Rate getByKeyFromCache(String keyName) {
        return rates.get(keyName);
    }

    public void removeByKeyFromCache(String keyName) {
        rates.remove(keyName);
    }

    public boolean isEmpty() {
        return rates.isEmpty();
    }

    public boolean isRateOld(String keyname) {

        long timeFromRateInCache = Long.parseLong(
                getByKeyFromCache(keyname).getTime());
        long timeOfRateInCache = System.currentTimeMillis() - timeFromRateInCache;

        LOGGER.info("O tempo da {} no cache é {}", keyname, timeOfRateInCache);

        return timeOfRateInCache > limitTimeForRateInCache ? Boolean.TRUE : Boolean.FALSE;
    }
}
