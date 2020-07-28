package sahan.abr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Security {
    private Integer id;
    private String login;
    private String password;
    private Integer idEmployee;

    public Security(String login, String password, Integer idEmployee) {
        this.login = login;
        this.password = password;
        this.idEmployee = idEmployee;
    }
}
