package com.mode.entity;

/**
 * Created by Lei on 3/23/16.
 */
public class Prize {

    private Integer id;
    private String type;
    private String title;
    private String description;
    private String image;
    private String subImage;
    private Integer startValue;
    private Integer endValue;
    private Integer credit;
    private Float price;
    private Integer isInBox;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStartValue() {
        return startValue;
    }

    public void setStartValue(Integer startValue) {
        this.startValue = startValue;
    }

    public Integer getEndValue() {
        return endValue;
    }

    public void setEndValue(Integer endValue) {
        this.endValue = endValue;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getSubImage() {
        return subImage;
    }

    public void setSubImage(String subImage) {
        this.subImage = subImage;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getIsInBox() {
        return isInBox;
    }

    public void setIsInBox(Integer isInBox) {
        this.isInBox = isInBox;
    }


    @Override
    public String toString() {
        return "Prize{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", subImage='" + subImage + '\'' +
                ", startValue=" + startValue +
                ", endValue=" + endValue +
                ", credit=" + credit +
                ", price=" + price +
                ", isInBox=" + isInBox +
                '}';
    }
}
