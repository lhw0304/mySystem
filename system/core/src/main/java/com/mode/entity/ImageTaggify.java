package com.mode.entity;


/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class ImageTaggify {

    private Integer id;
    private String taggifiedImage;
    private Integer authorId;
    private Integer draftId;
    private Long ctime;
    private Long utime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaggifiedImage() {
        return taggifiedImage;
    }

    public void setTaggifiedImage(String taggifiedImage) {
        this.taggifiedImage = taggifiedImage;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getDraftId() {
        return draftId;
    }

    public void setDraftId(Integer draftId) {
        this.draftId = draftId;
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
        return "ImageTaggify{" +
                "id=" + id +
                ", taggifiedImage='" + taggifiedImage + '\'' +
                ", authorId=" + authorId +
                ", draftId=" + draftId +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}


