package com.korber.myfilter.db.entities;

import com.korber.myfilter.db.entities.enumm.StatusFilter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "myfilter_audit")
@Entity
public class MyFilterAudit implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int version;

    @ManyToOne
    @JoinColumn(name = "filter_id")
    private MyFilter filterId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "data")
    private String data;

    @Column(name = "outputfilter")
    private String outputFilter;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private MyFilter parent;

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    private UUID createdBy;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedBy
    @Column(name = "updated_by", length = 50)
    private UUID updatedBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusFilter status;

    public MyFilterAudit(MyFilter filter) {
        MyFilterAudit(
                filter,
                filter.getUser(),
                filter.getName(),
                filter.getData(),
                filter.getOutputFilter(),
                filter.getScreen(),
                filter.getParent(),
                filter.getCreatedBy(),
                filter.getCreatedAt(),
                filter.getUpdatedBy(),
                filter.getUpdatedAt(),
                filter.getStatus());
    }

    public MyFilterAudit() {

    }

    void MyFilterAudit(MyFilter filterId, User user, String name, String data, String outputFilter, Screen screen, MyFilter parent, UUID createdBy, LocalDateTime createdAt, UUID updatedBy, LocalDateTime updatedAt, StatusFilter status) {
        this.filterId = filterId;
        this.user = user;
        this.name = name;
        this.data = data;
        this.outputFilter = outputFilter;
        this.screen = screen;
        this.parent = parent;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public int getVersion() {
        return version;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public String getOutputFilter() {
        return outputFilter;
    }

    public Screen getScreen() {
        return screen;
    }

    public MyFilter getParent() {
        return parent;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UUID getUpdatedBy() {
        return updatedBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public MyFilter getFilterId() {
        return filterId;
    }

    @Override
    public String toString() {
        return "MyFilterAudit{" +
                "version=" + version +
                ", filterId=" + filterId +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                ", outputFilter='" + outputFilter + '\'' +
                ", screen=" + screen +
                ", parent=" + parent +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", updatedBy=" + updatedBy +
                ", updatedAt=" + updatedAt +
                '}';
    }
}