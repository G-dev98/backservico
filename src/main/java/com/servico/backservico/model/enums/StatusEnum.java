package com.servico.backservico.model.enums;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = respostaDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusEnum {

    PENDENTE(1, "PENDENTE"),
    REALIZADO(2, "REALIZADO"),
    CANCELADO(3, "CANCELADO");

    @Id
    public Integer id;
    public String descricao;

    StatusEnum(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static Map<Object, Object> getValuesAsMap() {

        return Stream.of(StatusEnum.values())
                .collect(Collectors.toMap(k -> k.id, v -> v.id + " - " + v.descricao));

    }
}

class respostaDeserializer extends EnumDeserializer<StatusEnum> {
}
