package pl.coderslab.runapp.DTO.runner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RunnerCreateRequestDto {

    private String name;
    private String email;

}
