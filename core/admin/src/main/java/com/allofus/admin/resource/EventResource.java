package com.allofus.admin.resource;

import com.allofus.admin.adapters.CompanyMapper;
import com.allofus.admin.adapters.EventMapper;
import com.allofus.admin.service.CompanyService;
import com.allofus.admin.service.EventService;
import com.allofus.commons.ws.request.CompanyRequest;
import com.allofus.commons.ws.request.EventRequest;
import com.allofus.commons.ws.response.CompanyResponse;
import com.allofus.commons.ws.response.EventResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/event")
public class EventResource extends Resource<EventRequest, EventResponse, EventService, EventMapper> {
    public EventResource(EventMapper mapper, EventService service) {
        super(mapper, service);
    }
}
