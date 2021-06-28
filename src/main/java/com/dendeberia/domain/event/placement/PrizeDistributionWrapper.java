package com.dendeberia.domain.event.placement;

import java.util.List;

public class PrizeDistributionWrapper {
    List<PlacementWrapper> placementWrappers;

    public List<PlacementWrapper> getPlacementWrappers() {
        return placementWrappers;
    }

    public void setPlacementWrappers(List<PlacementWrapper> placementWrappers) {
        this.placementWrappers = placementWrappers;
    }
}
