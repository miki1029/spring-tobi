package springbook.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by miki on 15. 10. 17..
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String id;
    String name;
    String password;
}
