/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author liviu
 */
@Named(value = "newJSFManagedBean")
@SessionScoped
public class NewJSFManagedBean implements Serializable {

    
    private static final int DEFAULT_BUFFER_SIZE = 10240;  
       private String filePath = "/home/liviu/Synom/Synom/Web_App/Synom.zip";  
       public void downLoad() throws IOException {  
            FacesContext context = FacesContext.getCurrentInstance();  
            HttpServletResponse response = (HttpServletResponse) context  
                      .getExternalContext().getResponse();  
            File file = new File(filePath);  
            if (!file.exists()) {  
                 response.sendError(HttpServletResponse.SC_NOT_FOUND);  
                 return;  
            }  
            response.reset();  
            response.setBufferSize(DEFAULT_BUFFER_SIZE);  
            response.setContentType("application/octet-stream");  
            response.setHeader("Content-Length", String.valueOf(file.length()));  
            response.setHeader("Content-Disposition", "attachment;filename=\""  
                      + file.getName() + "\"");  
            BufferedInputStream input = null;  
            BufferedOutputStream output = null;  
            try {  
                 input = new BufferedInputStream(new FileInputStream(file),  
                           DEFAULT_BUFFER_SIZE);  
                 output = new BufferedOutputStream(response.getOutputStream(),  
                           DEFAULT_BUFFER_SIZE);  
                 byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];  
                 int length;  
                 while ((length = input.read(buffer)) > 0) {  
                      output.write(buffer, 0, length);  
                 }  
            } finally {  
                 input.close();  
                 output.close();  
            }  
            context.responseComplete();  
       }  
    
    
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public NewJSFManagedBean() {
    }
    
}
