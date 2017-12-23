/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.exceptions;

import com.zenith.user.response.ErrorMessage;
import com.zenith.user.response.ErrorMessages;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author calebschumake
 */
@Provider
/* Register exception with jersey framework to get json exception back */
public class RecordAlreadyExistsMapper implements ExceptionMapper<RecordAlreadyExistsException> {

    public Response toResponse(RecordAlreadyExistsException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), ErrorMessages.RECORD_ALREADY_EXISTS
                .name());
        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).type(MediaType.APPLICATION_JSON).build();
    }

}
