package com.PEA.webAsset.Entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Setter
@Getter
@Table(name = "tbConsideration")
@NoArgsConstructor
@EqualsAndHashCode
public class tbConsideration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cons_seq")
    @GenericGenerator(name = "cons_seq", strategy = "com.PEA.webAsset.Share.Generator.ConsiderationGeneratorId")
    @Column(name = "consider_id ", nullable = false, unique = true)
    private String considerId;
    @Column(name = "quotation")
    @NotNull(message = "quotation is null")
    private String quotation;
    @Column(name = "MimeType")
    private String MimeType;
    @Column(name = "size")
    private long size = 0;
    @Column(name = "hash", nullable = false, unique = true)
    private String hash;
    @Column(name = "data")
    private Byte data;
    public static  final int RADIX = 32;
    @Column(name = "dateReq")
    private LocalDateTime dateReq;
    @Column(name = "ppkApproved1")
    private Boolean ppkApproved1;
    @Column(name = "ppkApproved2")
    private Boolean ppkApproved2;

    public void setHash() throws NoSuchAlgorithmException {
        String transformedName = new StringBuilder().append(this.quotation).append(this.MimeType)
                .append(this.size).append(new Date().getTime()).toString();
        MessageDigest messageDigest = MessageDigest.getInstance("sha1");
        messageDigest.update(transformedName.getBytes(StandardCharsets.UTF_8));
        this.hash = new BigInteger(1,messageDigest.digest()).toString();
    }


}
