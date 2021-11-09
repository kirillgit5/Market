package com.kramar.Market.orders;

import com.kramar.Market.users.UserEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "ORDER")
public class OrderEntity {
    @Setter(AccessLevel.NONE)
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Setter(AccessLevel.PROTECTED)
    @ToString.Exclude
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(nullable = false)
    private UserEntity user;

    @Setter(AccessLevel.PROTECTED)
    @ToString.Exclude
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<PurchaseEntity> purchases;

    @Setter(AccessLevel.PROTECTED)
    private Deliverly deliverly;

    @Setter(AccessLevel.PROTECTED)
    private OrderStatus status;

    @Setter(AccessLevel.PROTECTED)
    private Payment payment;

    @Setter(AccessLevel.PROTECTED)
    private String deliverlyAddress;

    @Setter(AccessLevel.PROTECTED)
    private LocalDateTime deliverlyTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderEntity that = (OrderEntity) o;
        return  id != null && Objects.equals(id, that.id);
    }

    @Override
    public  int hashCode() { return Objects.hash(id); }
}