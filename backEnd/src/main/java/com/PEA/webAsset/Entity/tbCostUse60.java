package com.PEA.webAsset.Entity;

import lombok.*;

import jakarta.persistence.*;


@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "tb_cost_use60")
@Table(name = "tb_cost_use60", indexes = {
        @Index(name = "idx_cost_use60_id", columnList = "cost_use60_id")
})
@ToString
public class tbCostUse60 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cost_use60_id", unique = true)
    private Long costUse60Id;

    private String referenceDocumentNumber;

    private String costComponent;

    private String costComponentName;

    private String offsetAccountName;

    private String offsetAccountNumber;

    private String username;

    private String documentDate;

    private String postingDate;

    private Double valueCoCurr;

    private String details;

    public tbCostUse60(Long costUse60Id, String referenceDocumentNumber, String costComponent, String costComponentName, 
    String offsetAccountName, String offsetAccountNumber, String username, String documentDate,
    String postingDate, Double valueCoCurr, String details) {
        this.costUse60Id = costUse60Id;
        this.referenceDocumentNumber = referenceDocumentNumber;
        this.costComponent = costComponent;
        this.costComponentName = costComponentName;
        this.offsetAccountName = offsetAccountName;
        this.offsetAccountNumber = offsetAccountNumber;
        this.username = username;
        this.documentDate = documentDate;
        this.postingDate = postingDate;
        this.valueCoCurr = valueCoCurr;
        this.details = details;
    }
}
