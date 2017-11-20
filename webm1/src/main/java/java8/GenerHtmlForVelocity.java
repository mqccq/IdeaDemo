package java8;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2017/10/31.
 */
public class GenerHtmlForVelocity {
    private static final String DEF_ENCODING = "UTF-8";

    public static void main(String []args){
        GenerHtmlForVelocity generHtmlForVelocity = new GenerHtmlForVelocity();
        generHtmlForVelocity.doRelease();
    }

    /**
     * 生成页面主体
     * @author wanghjbuf
     */
    public void doRelease(){
        try {
            Properties p = initVelocityProperties();
            Velocity.init(p);

            Template template = getVelocityTemplate();
            VelocityContext context = initVelocityContext();
            BufferedWriter writer = getWriterStream();

            template.merge(context, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化VelocityProperties
     * @author wanghjbuf
     */
    public Properties initVelocityProperties() throws Exception{
        Properties p = new Properties();
        try{
            //指定模板文件存放位置
            p.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            p.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            p.setProperty(Velocity.ENCODING_DEFAULT, DEF_ENCODING);
            p.setProperty(Velocity.INPUT_ENCODING, DEF_ENCODING);
            p.setProperty(Velocity.OUTPUT_ENCODING, DEF_ENCODING);
        }catch(Exception e){
            e.printStackTrace();
        }
        return p;
    }

    /**
     * 初始化VelocityContext
     * @author wanghjbuf
     */
    public VelocityContext initVelocityContext() throws Exception{
        VelocityContext context = new VelocityContext();
        try{
            List<String> blog_list = new ArrayList<String>();
            context.put("englishName", "kun");
            context.put("teacherName", "mia");
            context.put("courseType", "试听课");

            context.put("pronunciationScore", 5);
            context.put("pronunciationRemark", "表达过程中始终灵活的使用各种发音技巧，升降语调，重读弱读，连读等， 能够表达微妙的差异，听者理解毫无障碍。");

            context.put("vocabularyScore", 5);
            context.put("vocabularyRemark", "自如得体的使用语法结构，除了出现英语为母语者也会犯的口误外，始终能使用正确的语法结构。");

            context.put("grammarScore", 5);
            context.put("grammarRemark", "词汇使用丰富，运用自如灵活，表达意思准确，熟练使用非常见的词汇及习语。");

            context.put("fluencyScore", 5);
            context.put("fluencyRemark", "表达流利，极少出现重复和自我纠正；连贯，衔接手段的使用完全恰当。");

            context.put("listeningScore", 5);
            context.put("listeningRemark", "在语速较快的情况下，能够迅速理解话题的内容；在出现生僻词的情况下可以通过情景猜测和推断出含义。");
            context.put("totalScore", 25);
        }catch(Exception e){
            e.printStackTrace();
        }
        return context;
    }

    /**
     * 获取Velocity模板
     */
    public Template getVelocityTemplate() throws Exception{
        Template template = new Template();
        try{
            template = Velocity.getTemplate("myblog.vm");
        }catch(Exception e){
            e.printStackTrace();
        }
        return template;
    }

    /**
     * 获取Velocity模板
     */
    public Template getVelocityTemplate(String velocityTemplateSource) throws Exception{
        Template template = new Template();
        try{
            template = Velocity.getTemplate(velocityTemplateSource);
        }catch(Exception e){
            e.printStackTrace();
        }
        return template;
    }

    /**
     * 获取Velocity写入流
     */
    public BufferedWriter getWriterStream() throws Exception{
        try{
            FileOutputStream fos = new FileOutputStream("D:/html/myEvalution.html");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, DEF_ENCODING));
            return writer;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Velocity写入流
     */
    public BufferedWriter getWriterStream(String writerStreamSource) throws Exception{
        try{
            FileOutputStream fos = new FileOutputStream(writerStreamSource);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, DEF_ENCODING));
            return writer;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
