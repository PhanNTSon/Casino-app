package io.phanson.casino_backend.entity;

import java.time.LocalDateTime;

import org.springframework.util.Assert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SystemLogs", schema = "dbo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SystemLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Actor", length = 255, nullable = false)
    private String actor;

    @Column(name = "Action", length = 255, nullable = false)
    private String action;

    @Column(name = "Target", length = 255, nullable = false)
    private String target;

    @Column(name = "Detail", length = 255, nullable = false)
    private String detail;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    public void setId(Long id) {
        this.id = id;
    }

    public void setActor(String actor) {
        Assert.hasText(actor, "Actor cannot be null or empty");
        Assert.isTrue(actor.length() <= 255, "Actor cannot be more than 255 chars");
        this.actor = actor;
    }

    public void setAction(String action) {
        Assert.hasText(action, "Action cannot be null or empty");
        Assert.isTrue(action.length() <= 255, "Action cannot be more than 255 chars");
        this.action = action;
    }

    public void setTarget(String target) {
        Assert.hasText(target, "Target cannot be null or empty");
        Assert.isTrue(target.length() <= 255, "Target cannot be more than 255 chars");
        this.target = target;
    }

    public void setDetail(String detail) {
        Assert.hasText(detail, "Actor cannot be null or empty");
        Assert.isTrue(detail.length() <= 255, "Actor cannot be more than 255 chars");
        this.detail = detail;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
