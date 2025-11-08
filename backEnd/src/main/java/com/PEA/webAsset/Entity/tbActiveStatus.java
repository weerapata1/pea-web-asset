package com.PEA.webAsset.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode
@Table(name= "tbActiveStatus")
@NoArgsConstructor
public class tbActiveStatus {
    @Id
    @Column(name = "active_id")
    private Long activeId;

    @Column(name = "activeStatusName")
    private String activeStatusName;
}
