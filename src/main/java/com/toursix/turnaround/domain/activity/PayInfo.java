package com.toursix.turnaround.domain.activity;

import com.toursix.turnaround.domain.kit.Kit;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class PayInfo {

    @Column(nullable = false)
    private int price;

    @OneToMany(targetEntity = Kit.class)
    private final List<Kit> kits = new ArrayList<>();

    @OneToMany(targetEntity = Kit.class)
    private final List<Kit> adKits = new ArrayList<>();
}
