package com.roik.portfolioadviser.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter(AccessLevel.NONE)
@Data
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class AbstractPriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;

    @Column(name = "price")
    private BigDecimal price;
}
