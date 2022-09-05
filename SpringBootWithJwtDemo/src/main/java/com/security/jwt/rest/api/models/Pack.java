package com.security.jwt.rest.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "charging_packs")
public class Pack {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name ="ID")
  private int packId;
  @Column(name="PACK_NAME")
  private String packName;
  @Column(name="PACK_TYPE")
  private String packType;
 
    
  public int getPackId() {
    return packId;
  }
  public void setPackId(int packId) {
    this.packId = packId;
  }
  public String getPackName() {
    return packName;
  }
  public void setPackName(String packName) {
    this.packName = packName;
  }
  
  
  public String getPackType() {
    return packType;
  }
  public void setPackType(String packType) {
    this.packType = packType;
  }
  
//  @Override
//  public String toString() {
//    return "PackId= " + getPackId() + " Pack Name= " + 
//        getPackName() + 
//        " Type= "+ getPackType();
//  }
}



