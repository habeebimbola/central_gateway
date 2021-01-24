package com.centralgateway.task;

import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.*;

public class BannerLoader {

    private Resource fileName;

    public void setFileName(Resource fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    public void fileLoader() throws IOException {

        InputStream fileNameInputStream   = fileName.getInputStream();
        BufferedReader bufferedReader = null ;
        String line;

        try{
            bufferedReader = new BufferedReader(new InputStreamReader(fileNameInputStream));

           while( (line = bufferedReader.readLine()) != null ) {
               System.out.println(line);
           }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bufferedReader != null ){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
