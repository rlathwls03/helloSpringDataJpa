package kr.ac.hansung.cse.hellospringdatajpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name은 필수 입력입니다.")
    private String name;

    @NotBlank(message = "Brand는 필수 입력입니다.")
    private String brand;

    @NotBlank(message = "MadeIn은 필수 입력입니다.")
    private String madeIn;

    // 1) BigDecimal 타입으로 변경
    // 2) precision=10, scale=2 로 컬럼을 DECIMAL(10,2) 형태로 생성
    @NotNull(message = "Price는 필수 입력입니다.")
    @Positive(message = "Price는 0보다 커야 합니다.")
    @Digits(integer = 10, fraction = 2, message = "Price는 소수점 둘째자리까지 허용합니다.")
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    public Product(String name, String brand, String madeIn, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.madeIn = madeIn;
        this.price = price;
    }
}