package com.wlxk.domain.tradebill;

import com.wlxk.domain.BasicDomain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 商品类
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@Entity
@Table(name = "tms_goods")
public class Goods extends BasicDomain {
    /** 交易单ID */
    private String tradeBillId;
    /** 交易单NO */
    private String tradeBillNo;
    /** 名称 */
    private String name;
    /** 包装 */
    private String packaging;
    /** 重量 */
    private Double weight;
    /** 重量单价 */
    private BigDecimal weightUp;
    /** 体积 */
    private Double volume;
    /** 体积单价 */
    private BigDecimal volumeUp;
    /** 商品类型 */
    private Integer goodsType;
    /** 声明价格 */
    private BigDecimal declaredValue;
    /** 理赔价格 */
    private BigDecimal safetyValue;

    public String getTradeBillId() {
        return tradeBillId;
    }

    public void setTradeBillId(String tradeBillId) {
        this.tradeBillId = tradeBillId;
    }

    public String getTradeBillNo() {
        return tradeBillNo;
    }

    public void setTradeBillNo(String tradeBillNo) {
        this.tradeBillNo = tradeBillNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public BigDecimal getWeightUp() {
        return weightUp;
    }

    public void setWeightUp(BigDecimal weightUp) {
        this.weightUp = weightUp;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public BigDecimal getVolumeUp() {
        return volumeUp;
    }

    public void setVolumeUp(BigDecimal volumeUp) {
        this.volumeUp = volumeUp;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public BigDecimal getDeclaredValue() {
        return declaredValue;
    }

    public void setDeclaredValue(BigDecimal declaredValue) {
        this.declaredValue = declaredValue;
    }

    public BigDecimal getSafetyValue() {
        return safetyValue;
    }

    public void setSafetyValue(BigDecimal safetyValue) {
        this.safetyValue = safetyValue;
    }
}
