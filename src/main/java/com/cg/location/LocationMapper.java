package com.cg.location;

import com.cg.location.dto.LocationRegionParam;
import com.cg.location.dto.LocationRegionResult;
import com.cg.model.LocationRegion;
import com.cg.model.Order;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {
    public LocationRegion toEntity(LocationRegionParam locationRegionParam) {
        return new LocationRegion();
//                .set(creationParam.getUserId());
    }


    public LocationRegionResult toDTO(Order entity) {
        return new LocationRegionResult();
    }

}
