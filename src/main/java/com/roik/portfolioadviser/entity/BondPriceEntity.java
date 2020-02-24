package com.roik.portfolioadviser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Table(name = "bond_pricies")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BondPriceEntity extends AbstractPriceEntity {

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bond_id", referencedColumnName = "id")
    private BondEntity bondEntity;
}
