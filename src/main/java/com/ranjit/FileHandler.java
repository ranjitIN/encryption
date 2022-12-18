package com.ranjit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileHandler {
    InputStream inputStream;
    BufferedWriter writer;

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void writeIntoFile(String filePath,String fileContent)throws IOException
    {
        writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(fileContent);
        writer.close();
    }

    public String readDataFromFile(String filePath)throws FileNotFoundException,IOException
    {
        final File file = new File(filePath);
        inputStream = new FileInputStream(file);
        String data =  new String(inputStream.readAllBytes());
        return data;
    }
}
