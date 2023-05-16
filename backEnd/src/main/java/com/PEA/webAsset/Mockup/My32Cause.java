// package com.PEA.webAsset.Mockup;

// import com.PEA.webAsset.Entity.tbCause;
// import com.PEA.webAsset.Repository.CauseRepository;
// import com.PEA.webAsset.Repository.DeviceTypeRepository;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.ApplicationArguments;
// import org.springframework.boot.ApplicationRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.stereotype.Component;

// import java.util.stream.Stream;

// @Component
// public class My32Cause implements ApplicationRunner{

//     private static final Logger logger = LoggerFactory.getLogger(My32Cause.class);

//     @Override
//     public void run(ApplicationArguments args) throws Exception {

//     }
//     @Bean
//     public ApplicationRunner Cause(CauseRepository causeRepository, DeviceTypeRepository deviceTypeRepository){
//         return (args -> {
//             causeRepository.save(new tbCause("เมนบอร์ดชำรุด/ไหม้",deviceTypeRepository.findByDtId(1L)));
//             causeRepository.save(new tbCause("ฮาร์ดดิสก์ชำรุด",deviceTypeRepository.findByDtId(1L)));
//             causeRepository.save(new tbCause("ปุ่มเปิดชำรุดดับเอง",deviceTypeRepository.findByDtId(1L)));
//             causeRepository.save(new tbCause("ระบบไฟลัดวงจร",deviceTypeRepository.findByDtId(1L)));
//             causeRepository.save(new tbCause("พาวเวอร์ซัพพลายชำรุด",deviceTypeRepository.findByDtId(1L)));
//             causeRepository.save(new tbCause("ประมวลผลช้า",deviceTypeRepository.findByDtId(1L)));
//             causeRepository.save(new tbCause("ระบบภายในชำรุด",deviceTypeRepository.findByDtId(1L)));

//             causeRepository.save(new tbCause("จอภาพ(Monitor)",deviceTypeRepository.findByDtId(2L)));
//             causeRepository.save(new tbCause("หลอดภาพชำรุด/CRT",deviceTypeRepository.findByDtId(2L)));
//             causeRepository.save(new tbCause("การ์ดจอไหม้",deviceTypeRepository.findByDtId(2L)));
//             causeRepository.save(new tbCause("จอภาพชำรุด/เสื่อมสภาพ",deviceTypeRepository.findByDtId(2L)));
//             causeRepository.save(new tbCause("แผงวงจรไหม้/ชำรุด",deviceTypeRepository.findByDtId(2L)));
//             causeRepository.save(new tbCause("จอสีเพี้ยน/มีเส้น",deviceTypeRepository.findByDtId(2L)));
//             causeRepository.save(new tbCause("หน้าจอแตก\">หน้าจอแตก",deviceTypeRepository.findByDtId(2L)));
//             causeRepository.save(new tbCause("สวิทช์ชำรุด",deviceTypeRepository.findByDtId(2L)));

//             causeRepository.save(new tbCause("หลอดสร้างภาพชำรุด",deviceTypeRepository.findByDtId(3L)));
//             causeRepository.save(new tbCause("เมนบอร์ดชำรุด/ไหม้ ",deviceTypeRepository.findByDtId(3L)));
//             causeRepository.save(new tbCause("ฟีดกระดาษชำรุด",deviceTypeRepository.findByDtId(3L)));
//             causeRepository.save(new tbCause("พาวเวอร์ซัพพลายชำรุด",deviceTypeRepository.findByDtId(3L)));
//             causeRepository.save(new tbCause("กระดาษติด/ยับ",deviceTypeRepository.findByDtId(3L)));
//             causeRepository.save(new tbCause("ผงหมึกรั่ว",deviceTypeRepository.findByDtId(3L)));
//             causeRepository.save(new tbCause("เปิดไม่ติด/สวิทช์ชำรุด",deviceTypeRepository.findByDtId(3L)));
//             causeRepository.save(new tbCause("ไม่มีไฟสถานะทำงาน",deviceTypeRepository.findByDtId(3L)));
//             causeRepository.save(new tbCause("ตัวหนังสือจาง",deviceTypeRepository.findByDtId(3L)));
//             causeRepository.save(new tbCause("หัวเข็มชำรุด",deviceTypeRepository.findByDtId(3L)));

//             causeRepository.save(new tbCause("แบตเตอรี่เสื่อม",deviceTypeRepository.findByDtId(4L)));
//             causeRepository.save(new tbCause("แผงวงจรไหม้/ชำรุด",deviceTypeRepository.findByDtId(4L)));
//             causeRepository.save(new tbCause("ไม่เก็บกระแสไฟ",deviceTypeRepository.findByDtId(4L)));
//             causeRepository.save(new tbCause("ปุ่มเปิด/ปิดไม่ทำงาน",deviceTypeRepository.findByDtId(4L)));

//         });
//     }
// }
