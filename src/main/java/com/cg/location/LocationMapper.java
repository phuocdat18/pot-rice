package com.cg.location;

import com.cg.location.dto.LocationRegionParam;
import com.cg.location.dto.LocationRegionResult;
import com.cg.model.LocationRegion;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationMapper {
    public LocationRegion toEntity(LocationRegionParam locationRegionParam) {
        return new LocationRegion();
//                .set(creationParam.getUserId());
    }


    public LocationRegionResult toDTO(LocationRegion entity) {
        return new LocationRegionResult();
    }

    public List<LocationRegionResult> toDTOList(List<LocationRegion> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
