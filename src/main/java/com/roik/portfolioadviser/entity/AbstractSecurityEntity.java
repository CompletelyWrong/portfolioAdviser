package com.roik.portfolioadviser.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Setter(AccessLevel.NONE)
@Data
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class AbstractSecurityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("currency")
    @Column(name = "currency")
    private String currency;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "country")
    private String country;

    @JsonProperty("exchange")
    @Column(name = "exchange_name")
    private String exchangeName;
}
