package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "tbSignatureStatus")
@Table(name = "tbSignatureStatus")
@Data @Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode


public class tbSignatureStatus {
    @Id
    @SequenceGenerator(sequenceName = "SignatureStatus_seq",name = "SignatureStatus_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SignatureStatus_seq")
    private Long id;

    private String signatureStatusName;
}
