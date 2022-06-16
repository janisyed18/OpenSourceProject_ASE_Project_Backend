package com.project.OpenSource.model;

import java.sql.Timestamp;

public class Project {

    private Integer id;
    private String name;
    private String description;
    private String createdBy;
    private String status;
    private String license;
    private Timestamp createdDate;
    private Timestamp acknowledgedDate;
    private String acknowledgedBy;
    private String reason;
    private Timestamp lastModifiedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getAcknowledgedDate() {
        return acknowledgedDate;
    }

    public void setAcknowledgedDate(Timestamp acknowledgedDate) {
        this.acknowledgedDate = acknowledgedDate;
    }

    public String getAcknowledgedBy() {
        return acknowledgedBy;
    }

    public void setAcknowledgedBy(String acknowledgedBy) {
        this.acknowledgedBy = acknowledgedBy;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", status='" + status + '\'' +
                ", license='" + license + '\'' +
                ", createdDate=" + createdDate +
                ", acknowledgedDate=" + acknowledgedDate +
                ", acknowledgedBy='" + acknowledgedBy + '\'' +
                ", reason='" + reason + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
