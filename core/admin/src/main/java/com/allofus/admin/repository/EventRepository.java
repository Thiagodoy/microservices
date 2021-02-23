package com.allofus.admin.repository;

import com.allofus.admin.model.Event;
import com.allofus.admin.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("eventRepository")
public interface EventRepository extends JpaRepository<Event,Long>, JpaSpecificationExecutor<Event> {
}
