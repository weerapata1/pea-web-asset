package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data @Getter @Setter
@Table(name = "tb_contract")
@Entity(name = "tb_contract")
@NoArgsConstructor
@EqualsAndHashCode
public class tbCommitment {
    @Id
    @GeneratedValue(generator = "cont_id",strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "cont_id", sequenceName = "cont_id")
    @Column(name = "cont_id", nullable = false)
    private Long cont_id;

    private String contract_no; // หมายเลขสัญญา
    private Integer contract_quantity;  // จำนวนชุดในสัญญา
    private String contract_details; // รายละเอียดของสัญญา
    private LocalDateTime date_pickedUp;  // วันที่รับของ
    private String emp_signature;  // เก็บลายเซ็นผู้รับเครื่อง
    private String contract_note; // ช่องหมายเหตุ

//    -------------------------------------------------------------- joinTable;

//    //   Join tbDevice.class------------------------------
//    @OneToMany(mappedBy = "tbCommitment")
//    private List<tbDevice> tbDevices = new ArrayList<tbDevice>();




}