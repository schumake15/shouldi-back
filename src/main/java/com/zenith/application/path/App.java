/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.application.path;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * 
 * @author calebschumake
 */
/* Application path will be the first part of any URI to
   access a web resource. For example: http://localhost:8080/api/
*/
@ApplicationPath("api") 
public class App extends Application {
    
    
}