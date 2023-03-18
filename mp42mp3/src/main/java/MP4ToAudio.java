import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import java.io.File;
import java.util.UUID;

/**
 * @author panzhixin
 * @date 2022/9/2
 * @description
 */

public class MP4ToAudio {
    public static void mp4ToAudio(String sourceFilePath){
        System.out.println("开始提取音频文件");
        File file=new File("sourceFilePath");
        FFmpegFrameGrabber ffg=new FFmpegFrameGrabber("sourceFilePath");
        Frame frame=null;
        FFmpegFrameRecorder ffr=null;
        String fileName=null;
        try{
            ffg.start();
            fileName=file.getAbsolutePath()+ UUID.randomUUID()+".mp3";
            System.out.println("--文件名-->>"+fileName);
            ffr=new FFmpegFrameRecorder(fileName,ffg.getAudioChannels());
            ffr.setFormat("mp3");
            ffr.setSampleRate(ffg.getSampleRate());
            ffr.setTimestamp(ffg.getTimestamp());
            ffr.setAudioQuality(0);

            ffr.start();
            int index = 0;
            while (true) {
                frame = ffg.grab();
                if (frame == null) {
                    System.out.println("视频处理完成");
                    break;
                }
                if (frame.samples != null) {
                    ffr.recordSamples(frame.sampleRate, frame.audioChannels, frame.samples);
                }
                System.out.println("帧值=" + index);
                index++;
            }
            ffr.stop();
            ffr.release();
            ffg.stop();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String sourceFilePath = "H:\\morning.mp4";
        mp4ToAudio(sourceFilePath);
    }
}