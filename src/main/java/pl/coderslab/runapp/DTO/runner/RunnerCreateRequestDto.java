package pl.coderslab.runapp.DTO.runner;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RunnerCreateRequestDto {


    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Email is required")
    @Email
    private String email;

}
