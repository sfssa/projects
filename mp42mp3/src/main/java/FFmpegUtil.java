import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author panzhixin
 * @date 2022/9/3
 * @description
 */

public class FFmpegUtil {
    public static void vedioToPcm(String vedioUrl,String pcmUrl,String ffmpegUrl) throws InterruptedException, IOException {
        List<String> commend = new ArrayList<String>();
        commend.add(ffmpegUrl);
        commend.add("-y");
        commend.add("-i");
        commend.add(vedioUrl);
        commend.add("-acodec");
        commend.add("pcm_s16le");
        commend.add("-f");
        commend.add("s16le");
        commend.add("-ac");
        commend.add("1");
        commend.add("-ar");
        commend.add("16000");
        commend.add(pcmUrl);
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(commend);
        builder.redirectErrorStream(true);
        Process process = builder.start();
        process.waitFor();// 等待进程执行结束
    }
    public static void main(String[] args) throws InterruptedException, IOException {
        vedioToPcm("D:\\test\\vedio\\123456.mp4","D:\\test\\vedio\\123456.pcm","D:\\ffmpeg-static\\bin\\ffmpeg.exe");
    }
}