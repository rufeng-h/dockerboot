package com.windcf.dockerboot.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author : chunf
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Long id;

    private String username;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String gender;

    private LocalDate birth;

    private static final long serialVersionUID = 1L;
}