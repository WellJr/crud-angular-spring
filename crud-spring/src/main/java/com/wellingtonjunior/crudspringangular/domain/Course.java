package com.wellingtonjunior.crudspringangular.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.wellingtonjunior.crudspringangular.enums.Category;
import com.wellingtonjunior.crudspringangular.enums.converters.CatetoryConverter;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Data
@Entity
@Where(clause = "status = 'Ativo'")
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
//@Table(name = "cursos")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull
    @Length(min =5, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = CatetoryConverter.class) //<-- Converte ENUM para String e String para ENUM
    private Category category;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Ativo|Inativo")
    @Column(length = 10, nullable = false)
    private String status = "Ativo";

}
