package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@Table(name = "tbCause")
@Entity(name = "tbCause")
@NoArgsConstructor
@EqualsAndHashCode
public class tbCause {
    @Id
    @SequenceGenerator(name = "cas_seq", sequenceName = "cas_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cas_seq")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String causeName;

    @ManyToOne(targetEntity = tbDeviceType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "deviceType_id", insertable = true, referencedColumnName = "dt_id")
    private tbDeviceType deviceType;

    public tbCause(String causeName, tbDeviceType deviceType) {
        this.causeName = causeName;
        this.deviceType = deviceType;
    }
}
