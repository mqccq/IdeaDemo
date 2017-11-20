package java7;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */
public class fileDemo {
    public static void main(String[] args){

        try(InputStream inputStream = new FileInputStream("/home/biezhi/a.txt")){
            inputStream.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(20000);
            FileInputStream fis = new FileInputStream("/a/b.txt");
        } catch (InterruptedException | FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("file.txt"));
            StringBuilder sb = new StringBuilder();
            String line      = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //要创建一个Path对象有多种方法，首先是final类Paths的两个static方法，如何从一个路径字符串来构造Path对象：
        Path path = Paths.get("/home/biezhi","a.txt");
        Path path1 = Paths.get("/home/biezhi/a.txt");
        URI uri = URI.create("file:////home/biezhi/a.txt");
        Path pathURI = Paths.get(uri);
        //通过FileSystems构造
        Path filePath = FileSystems.getDefault().getPath("/home/biezhi", "a.txt");
        //Path、URI、File之间的转换
        File file = new File("/home/biezhi/a.txt");
        Path p1 = file.toPath();
        p1.toFile();
        file.toURI();
        //你可以使用Files类快速实现文件操作，例如读取文件内容:
        try {
            byte[] data = Files.readAllBytes(Paths.get("/home/biezhi/a.txt"));
            String content = new String(data, StandardCharsets.UTF_8);
            //如果希望按照行读取文件，可以调用
            List<String> lines = Files.readAllLines(Paths.get("/home/biezhi/a.txt"));
            //你想将字符串写入到文件可以调用
            Files.write(Paths.get("/home/biezhi/b.txt"),"Hello JDK7!".getBytes());
            //你也可以按照行写入文件，Files.write方法的参数中支持传递一个实现Iterable接口的类实例。 将内容追加到指定文件可以使用write方法的第三个参数OpenOption:
            Files.write(Paths.get("/home/biezhi/b.txt"), "Hello JDK7!".getBytes(),
                    StandardOpenOption.APPEND);
            InputStream ins = Files.newInputStream(path);
            OutputStream ops = Files.newOutputStream(path);
            Reader reader = Files.newBufferedReader(path);
            Writer writer = Files.newBufferedWriter(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if(!Files.exists(path)){
                Files.createFile(path);
                Files.createDirectory(path);
            }
            Files.list(path);
            Files.walk(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
