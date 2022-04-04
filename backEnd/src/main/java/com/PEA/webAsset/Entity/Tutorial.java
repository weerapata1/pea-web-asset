package com.PEA.webAsset.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@Table(name = "tutorials")
public class Tutorial {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "published")
    private boolean published;

    @ManyToOne(targetEntity = tbDeviceType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "dt_id" , insertable = true ,referencedColumnName = "dt_id")
    private tbDeviceType tbDeviceType;

//    public void setTbDeviceType(long parseLong) {
//        this.tbDeviceType = parseLong;
//    }

//    @Override
//    public String toString() {
//        return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
//    }
}
