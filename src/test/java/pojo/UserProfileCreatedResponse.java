package pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileCreatedResponse extends UserProfileRequest {

    private String id;
    private String createdAt;

}
