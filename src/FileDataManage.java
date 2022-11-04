import java.io.*;

public class FileDataManage {
    
    private File file;
    private FileWriter fWriter;
    private BufferedWriter bWriter;

    private FileReader fReader;
    private BufferedReader bReader;

    public FileDataManage(String path){

        try{
            this.file = new File(path);
            this.fWriter = new FileWriter(this.file);
            this.bWriter = new BufferedWriter(this.fWriter);
            this.fReader = new FileReader(this.file);
            this.bReader = new BufferedReader(this.fReader);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void FileWrite(String data){
        try{
            bWriter.write(data);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String FileRead(){
        String data = "";
        try{
            String temp = "";

            while ((temp = bReader.readLine()) != null){
                data += temp;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }
}
