package br.com.ungaratto93.fx.domain.rate;

import br.com.ungaratto93.fx.domain.fiat.Symbol;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@ToString
public class RateCache {

    @JsonProperty("rates")
    private Map<String, Rate> rates = new HashMap<>();

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


    public Rate rateByKeyFromCache(String keyName) {
        return rates.get(keyName);
    }
}
