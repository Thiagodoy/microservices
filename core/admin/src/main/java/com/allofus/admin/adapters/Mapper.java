package com.allofus.admin.adapters;

import com.allofus.admin.model.Model;
import com.allofus.commons.ws.request.Request;
import com.allofus.commons.ws.response.Response;


public interface Mapper< M extends Model,I extends Request, O extends Response> {

       M toEntity(I request);
       O toResponse(M model);

}
