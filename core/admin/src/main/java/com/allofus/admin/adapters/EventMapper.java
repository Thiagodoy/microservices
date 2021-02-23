package com.allofus.admin.adapters;


import com.allofus.admin.model.Event;
import com.allofus.commons.ws.request.EventRequest;
import com.allofus.commons.ws.response.EventResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface EventMapper extends com.allofus.admin.adapters.Mapper<Event, EventRequest, EventResponse> {
    EventMapper INSTANCE = (EventMapper) Mappers.getMapper(EventMapper.class);
}
