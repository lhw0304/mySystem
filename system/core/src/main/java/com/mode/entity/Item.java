package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class Item {

    @JsonView(View.Detail.class)
    private Integer id;

    @JsonView(View.Detail.class)
    private Integer type;

    @JsonView(View.Detail.class)
    private String itemName;

    @JsonView(View.Detail.class)
    private Integer brandId;

    @JsonView(View.Detail.class)
    private String defaultImage;

    @JsonView(View.Detail.class)
    private String extraImage;

    @JsonView(View.Detail.class)
    private String description;

    @JsonView(View.Detail.class)
    private Float price;

    @JsonView(View.Detail.class)
    private Integer onSale;

    @JsonView(View.Detail.class)
    private Long saleTime;

    @JsonView(View.Detail.class)
    private Float salePrice;

    @JsonView(View.Detail.class)
    private Integer salePercent;

    private String sku;

    @JsonView(View.Detail.class)
    private String size;

    @JsonView(View.Detail.class)
    private String color;

    @JsonView(View.Detail.class)
    private String productLink;

    private Integer status;

    @JsonView(View.Detail.class)
    private Integer views;

    @JsonView(View.Detail.class)
    private Integer saves;

    @JsonView(View.Detail.class)
    private Integer shares;

    @JsonView(View.Detail.class)
    private Long ctime;

    @JsonView(View.Detail.class)
    private Long utime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public String getExtraImage() {
        return extraImage;
    }

    public void setExtraImage(String extraImage) {
        this.extraImage = extraImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getOnSale() {
        return onSale;
    }

    public void setOnSale(Integer onSale) {
        this.onSale = onSale;
    }

    public Long getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Long saleTime) {
        this.saleTime = saleTime;
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(Integer salePercent) {
        this.salePercent = salePercent;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getSaves() {
        return saves;
    }

    public void setSaves(Integer saves) {
        this.saves = saves;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getUtime() {
        return utime;
    }

    public void setUtime(Long utime) {
        this.utime = utime;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", type=" + type +
                ", itemName='" + itemName + '\'' +
                ", brandId=" + brandId +
                ", defaultImage='" + defaultImage + '\'' +
                ", extraImage='" + extraImage + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", onSale=" + onSale +
                ", saleTime=" + saleTime +
                ", salePrice=" + salePrice +
                ", salePercent=" + salePercent +
                ", sku='" + sku + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", productLink='" + productLink + '\'' +
                ", status=" + status +
                ", views=" + views +
                ", saves=" + saves +
                ", shares=" + shares +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
