package com.PEA.webAsset.Entity;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Table(name = "tb_contract")
@Entity(name = "tb_contract")
@NoArgsConstructor
@EqualsAndHashCode
public class tbContract {
    @Id
    @GeneratedValue(generator = "cont_id", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "cont_id", sequenceName = "cont_id")
    @Column(name = "cont_id", nullable = false)
    private Long cont_id;

    private String contract_concat; // มูลค่าแรกเข้า-วันที่ได้รับ
    private String contract_description; // รายละเอียดของสัญญา
    private String contract_no; // หมายเลขสัญญา
    private Integer contract_quantity; // จำนวนชุดในสัญญา
    // private String contract_details;

    // Join tbDeviceType.class------------------------------
    @ManyToOne(targetEntity = tbDeviceType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "dt_id", insertable = true, referencedColumnName = "dt_id", nullable = true)
    private tbDeviceType tbDeviceType;

    private String partner; // บ.คู่สัญญา
    private String partner_tel; // เบอร์ติดต่อ บ.คู่สัญญา
    // private LocalDateTime date_pickedUp; // วันที่รับของ
    // private Long emp_id; // หมายเลขประจำตัวผู้รับเครื่อง @@JoinTable
    private String emp_signature; // เก็บลายเซ็นผู้รับเครื่อง
    private String install_at; // สถานที่ติดตั้ง

    private LocalDate start_date; // วันที่รับของ
    private LocalDate exp_date; // วันที่รับของ
    private Integer warranty_period; // จำนวนปีรับประกัน
    private String pic; // Path รูป
    private String replace_contract; // ทดแทนสัญญา
    private String contract_note; // หมายเหตุ
    // -------------------------------------------------------------- joinTable;

    // Join tbDevice.class------------------------------
    // @OneToMany(mappedBy = "tbCommitment")
    // private List<tbDevice> tbDevices = new ArrayList<tbDevice>();

}