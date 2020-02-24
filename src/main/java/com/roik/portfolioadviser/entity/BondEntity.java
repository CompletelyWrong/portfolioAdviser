package com.roik.portfolioadviser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "bonds")
@Entity
@NoArgsConstructor
public class BondEntity extends AbstractSecurityEntity {
    @Column(name = "start_of_issue", columnDefinition = "DATE")
    private LocalDate startOfIssue;

    @Column(name = "end_of_issue", columnDefinition = "DATE")
    private LocalDate endOfIssue;

    @Column(name = "percentage_of_profit")
    private BigDecimal percentageOfProfit;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "bondEntity", cascade = CascadeType.ALL)
    private List<BondPriceEntity> priceEntities;
}
