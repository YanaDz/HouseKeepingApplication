package com.dziadkouskaya.housekeeping.entity.filters;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class SearchRequest extends PageDescriptionRequest {
    private String search;
}
