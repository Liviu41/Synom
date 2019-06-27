
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author liviu
 */
public class downloadFile {
    
    public void df() {

    File file = new File("/home/liviu/Synom/Synom/Web_App/Synom.zip");
    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  

    response.setHeader("Content-Disposition", "attachment;filename=file.txt");  
    response.setContentLength((int) file.length());  
    ServletOutputStream out = null;  
    try {  
        FileInputStream input = new FileInputStream(file);  
        byte[] buffer = new byte[1024];  
        out = response.getOutputStream();  
        int i = 0;  
        while ((i = input.read(buffer)) != -1) {  
            out.write(buffer);  
            out.flush();  
        }  
        FacesContext.getCurrentInstance().getResponseComplete();  
    } catch (IOException err) {  
        err.printStackTrace();  
    } finally {  
        try {  
            if (out != null) {  
                out.close();  
            }  
        } catch (IOException err) {  
            err.printStackTrace();  
        }  
    }  

}
    
}
