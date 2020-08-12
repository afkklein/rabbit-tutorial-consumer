package com.afkklein.rabbittutorialconsumer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person implements Serializable {
    private String name;
    private Integer collegeCompletedYear;
    private LocalDate bornAt;
    private Boolean active;
}
