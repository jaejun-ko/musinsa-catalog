package com.assignment.musinsacatalog.domain.brand;

import com.assignment.musinsacatalog.common.exception.InvalidParamException;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.type.YesNoConverter;

@Getter
@Entity
@Table(
        name = "brands"
)
@NoArgsConstructor
@ToString
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Convert(converter = YesNoConverter.class)
    private Boolean deleted;

    @Builder
    public Brand(String name) {

        if (StringUtils.isBlank(name)) throw new InvalidParamException("Brand.name");

        this.name = name;
        this.deleted = Boolean.FALSE;
    }

    public void changeName(String name) {

        if (StringUtils.isBlank(name)) throw new InvalidParamException("Brand.name");

        this.name = name;
    }

    public void delete() {

        this.deleted = Boolean.TRUE;
    }
}
