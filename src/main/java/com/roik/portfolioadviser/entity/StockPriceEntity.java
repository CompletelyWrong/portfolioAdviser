package com.roik.portfolioadviser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@Builder
@Table(name = "stock_pricies")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("values")
public class StockPriceEntity extends AbstractPriceEntity {

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private StockEntity stockEntity;
}
