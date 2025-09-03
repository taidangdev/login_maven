package org.example.trangdangnhap.model;

public class Category {
    private Long id;
    private String name;
    private String description;
    private Long userId;
    private String icon;
    private String createdAt;
    private String updatedAt;

    public Category() {
    }

    public Category(String name, String description, Long userId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
    }

    public Category(Long id, String name, String description, Long userId, String icon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
