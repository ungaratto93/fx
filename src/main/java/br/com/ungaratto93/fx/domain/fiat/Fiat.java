package br.com.ungaratto93.fx.domain.fiat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fiat {
    private Symbol from;
    private Double amount;
    private Symbol to;

}
