package com.roik.portfolioadviser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "stocks")
@NoArgsConstructor
@Entity
public class StockEntity extends AbstractSecurityEntity {
    @Column(name = "percentage_of_dividend")
    private BigDecimal percentageOfDividend;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "stockEntity", cascade = CascadeType.ALL)
    private List<StockPriceEntity> priceEntity;
}
