package pl.coderslab.runapp.DTO.runner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RunnerResponseDto {

    private Long id;
    private String name;
    private String email;


}
