package br.com.ungaratto93.fx.unit.domain.fiat;

import br.com.ungaratto93.fx.domain.fiat.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SymbolTest {

    @Test
    void mustRetunLengthOfSymbolEnums() {
        final var length = Symbol.getLength();
        Assertions.assertEquals(152, length);
    }

}
