package com.spblue4422.divers.common.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public abstract class EntityDate {
    @Column(name="createdAt")
    protected Date createdAt;

    @Column(name="deletedAt")
    protected Date deletedAt;

    public EntityDate() {
        this.createdAt = new Date();
        this.deletedAt = null;
    }
}
