package de.dhbw.ravensburg.wp.mymoviedatabase.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CastDTO {

    private String forename;
    private String surname;
    private String dateOfBirth;
    private String profileImgUrl;
    private double height;

}
