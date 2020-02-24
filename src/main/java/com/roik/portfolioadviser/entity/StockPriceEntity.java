package com.roik.portfolioadviser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Table(name = "stock_pricies")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class StockPriceEntity extends AbstractPriceEntity {

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private StockEntity stockEntity;
}
