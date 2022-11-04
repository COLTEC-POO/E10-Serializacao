import java.io.*;

public class BinDataManage {
    

   
    private File file;
    private FileOutputStream fWriter;
    private ObjectOutputStream bWriter;

    private FileInputStream fReader;
    private ObjectInputStream bReader;

    public BinDataManage(String path_fileName){

        try{
            this.file = new File("./" + path_fileName + ".ser");
            this.fWriter = new FileOutputStream(this.file);
            this.bWriter = new ObjectOutputStream(this.fWriter);
            this.fReader = new FileInputStream(this.file);
            this.bReader = new ObjectInputStream(this.fReader);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void FileWrite(Object obj){
        try{
            bWriter.writeObject(obj);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public Object FileRead(){
        Object data = null;

        try {
            data = bReader.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        
        return data;
    }
}
