package com.roik.portfolioadviser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.roik.portfolioadviser.configuration.StockDeserializer;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Setter(AccessLevel.NONE)
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "stocks")
@NoArgsConstructor
@Entity
@JsonDeserialize(using = StockDeserializer.class)
public class StockEntity extends AbstractSecurityEntity {
    @Column(name = "percentage_of_dividend")
    private BigDecimal percentageOfDividend;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "stockEntity", cascade = CascadeType.ALL)
    private List<StockPriceEntity> priceEntity;
}
