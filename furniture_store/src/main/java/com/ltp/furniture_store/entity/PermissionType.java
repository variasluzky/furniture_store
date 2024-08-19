package com.ltp.furniture_store.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permission_types")
public class PermissionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Short permissionId;

    @NonNull
    @Column(name = "permission_status", nullable = false)
    private String permissionStatus;

    public String getPermissionTypeName() {
        return this.permissionStatus;
    }
}
