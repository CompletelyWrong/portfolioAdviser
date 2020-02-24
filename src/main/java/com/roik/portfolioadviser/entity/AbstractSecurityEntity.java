package com.roik.portfolioadviser.entity;

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

    @Column(name = "currency")
    private String currency;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "country")
    private String country;

    @Column(name = "exchange_name")
    private String exchangeName;
}
