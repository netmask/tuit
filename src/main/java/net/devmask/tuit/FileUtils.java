package net.devmask.tuit;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author <a href="mailto:jonathan@devmask.net"> Jonathan Garay </a>
 *         11/02/12 Creado
 */
public class FileUtils {
    
    public static boolean storeFile(MultipartFile file, String path){
            try {
                File o = new File(".");
                FileOutputStream fos = new FileOutputStream(o.getCanonicalPath()+path+file.getOriginalFilename());
                fos.write(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        return true;
    }

    public static boolean storeImage(MultipartFile file){
        return  storeFile(file,"/target/tuit-1.0-SNAPSHOT/resources/img");
    }
}
