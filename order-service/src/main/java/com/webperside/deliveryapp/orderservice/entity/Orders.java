package com.webperside.deliveryapp.orderservice.entity;

import com.webperside.deliveryapp.orderservice.entity.emmbedded.AddressPoint;
import com.webperside.deliveryapp.orderservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Orders implements Serializable {

    private static final long serialVersionUID = -2169852524593848520L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min=5, max = 100, message = "Title size must be between 5 and 100")
    @Column(name = "title")
    private String title;

    @Size(max = 255, message = "Description max size must be 255")
    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "courier_id")
    private Long courierId;

    @Column(name = "created_by")
    @CreatedBy
    private Long createdBy;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "start_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "start_longitude"))
    })
    private AddressPoint startAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "des_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "des_longitude"))
    })
    private AddressPoint destinationAddress;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
}
