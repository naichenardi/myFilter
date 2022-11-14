package com.korber.myfilter.db.entities;

import com.korber.myfilter.db.entities.enumm.StatusFilter;
import com.korber.myfilter.exception.ServiceException;
import com.korber.myfilter.listeners.AuditingService;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "myfilter")
@Entity
@EntityListeners(AuditingService.class)
@SQLDelete(sql = "UPDATE myfilter SET deleted = true, status = 'DELETED' WHERE id=uuid(?)")
@Where(clause = "deleted = false")
public class MyFilter implements Serializable, Cloneable {
    @Id
    @Column(name = "id", nullable = false)
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
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

    @Column(name = "version")
    private int version;

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    private UUID createdBy;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedBy
    @Column(name = "updated_by", length = 50)
    private UUID updatedBy;

    @Enumerated(EnumType.STRING)
    private StatusFilter status;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    public Boolean getDeleted() {
        return deleted;
    }

    public MyFilter() {
    }

    public MyFilter getParent() {
        return parent;
    }

    public void setParent(MyFilter parent) {
        this.parent = parent;
    }

    public Integer getVersion() {
        return version;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public String getOutputFilter() {
        return outputFilter;
    }

    public void setOutputFilter(String outputFilter) {
        this.outputFilter = outputFilter;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getId() {
        return id;
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

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedBy(UUID updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void addVersion(){
        ++this.version;
    }

    public StatusFilter getStatus() {
        return status;
    }

    public void setStatus(StatusFilter status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MyFilter{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                ", outputFilter='" + outputFilter + '\'' +
                ", screen=" + screen +
                ", version=" + version +
                '}';
    }

    public MyFilter createBranch()  {
        try {
            MyFilter clone = (MyFilter) super.clone();

            clone.setParent(this);
            clone.setStatus(StatusFilter.DRAFT);
            clone.id = UUID.randomUUID();

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new ServiceException();
        }
    }

    public void fillFields(MyFilter myFilter) {
        setData(myFilter.getData());
        setName(myFilter.getName());
        setScreen(myFilter.getScreen());
        setUser(myFilter.getUser());
        setOutputFilter(myFilter.getOutputFilter());
    }
}