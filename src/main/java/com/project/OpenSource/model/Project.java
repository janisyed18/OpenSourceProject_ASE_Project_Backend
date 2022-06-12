package com.project.OpenSource.model;

import java.sql.Timestamp;

public class Project {

    private Integer id;
    private String name;
    private String description;
    private String proposedBy;
    private String status;
    private String license;
    private Timestamp createdDate;
    private Timestamp approvedDate;
    private String approvedBy;
    private String rejectionReason;
    private Timestamp lastModifiedDate;
    private Timestamp rejectedDate;
    private String rejectedBy;

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

    public String getProposedBy() {
        return proposedBy;
    }

    public void setProposedBy(String proposedBy) {
        this.proposedBy = proposedBy;
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

    public Timestamp getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Timestamp approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Timestamp getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(Timestamp rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    public String getRejectedBy() {
        return rejectedBy;
    }

    public void setRejectedBy(String rejectedBy) {
        this.rejectedBy = rejectedBy;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", proposedBy='" + proposedBy + '\'' +
                ", status='" + status + '\'' +
                ", license='" + license + '\'' +
                ", createdDate=" + createdDate +
                ", approvedDate=" + approvedDate +
                ", approvedBy='" + approvedBy + '\'' +
                ", rejectionReason='" + rejectionReason + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", rejectedDate=" + rejectedDate +
                ", rejectedBy='" + rejectedBy + '\'' +
                '}';
    }
}
