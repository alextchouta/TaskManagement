package org.sid.taskmanagement.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserForm {
    String username;
    String password;
    String confirmedPassword;
}
