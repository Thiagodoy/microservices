package com.allofus.admin.resource;

import com.allofus.admin.adapters.Mapper;
import com.allofus.admin.model.Model;
import com.allofus.admin.service.Service;
import com.allofus.commons.ws.request.Request;
import com.allofus.commons.ws.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class Resource<I extends Request, O extends Response, S extends Service, M extends Mapper> {

    private final S service;
    private final M mapper;

    public Resource(M mapper, S service) {
        this.mapper = mapper;
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(Long id) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<O> get(Long id) throws Exception {
        Model model = this.service.find(id);
        return ResponseEntity.ok((O) mapper.toResponse(model));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity put(I request) throws Exception {

        Model model = mapper.toEntity(request);
        this.service.update(model);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity post(I request) {
        Model model = mapper.toEntity(request);
        this.service.save(model);
        return ResponseEntity.ok().build();
    }


}
