package com.allofus.admin.service;

import com.allofus.admin.model.Company;
import com.allofus.admin.model.Event;
import com.allofus.admin.repository.CompanyRepository;
import com.allofus.admin.repository.EventRepository;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
public class EventService extends Service<Event, EventRepository>{
    public EventService(@Qualifier("eventRepository") EventRepository companyRepository) {
        super(companyRepository);
    }
}
