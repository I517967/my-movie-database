package de.dhbw.ravensburg.wp.mymoviedatabase.mapper;

import de.dhbw.ravensburg.wp.mymoviedatabase.dto.AwardDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.model.Award;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AwardMapper {

    //Attribut movies wird automatisch ignoriert, da die Eigenschaft im DTO
    // nicht existiert
    @Mapping(source = "year", target = "celebrationYear")
    AwardDTO awardToAwardDTO(Award award);
    List<AwardDTO> awardsToAwardsDTO(List<Award> awards);

    @InheritInverseConfiguration
    Award awardDTOToAward(AwardDTO awardDTO);
    List<Award> awardDTOsToAwards(List<AwardDTO> awardDTOList);
}

