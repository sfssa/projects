import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author panzhixin
 * @date 2022/9/3
 * @description
 */

public class FfmpegUtils {
    private static String ffplay = "D:\\ffmpeg-4.3.1-2021-01-01-essentials_build\\bin\\ffplay.exe";
    private static String ffmpeg = "D:\\ffmpeg-4.3.1-2021-01-01-essentials_build\\bin\\ffmpeg.exe";
    private static String ffprobe = "D:\\ffmpeg-4.3.1-2021-01-01-essentials_build\\bin\\ffprobe.exe";
    /**
     * 提取的音频、合成的视频存放路径，不存在会自动创建
     */
    private static String saveMediaPath = "D:\\ffmpegMedia\\";

    /**
     * 保存音频、视频的临时文件夹，不存在会自动创建
     */
    private static String tempMediaPath = "D:\\ffmpegMedia\\temp\\";
    /**
     * 保存图片截图的文件夹，不存在会自动创建
     */
    private static String picturMediaPath = "D:\\ffmpegMedia\\pictur\\";

    static {
        //如果没有文件夹，则创建
        File saveMediaFile = new File(saveMediaPath);
        if (!saveMediaFile.exists() && !saveMediaFile.isDirectory()) {
            saveMediaFile.mkdirs();
        }
        File tempMediaFile = new File(tempMediaPath);
        if (!tempMediaFile.exists() && !tempMediaFile.isDirectory()) {
            tempMediaFile.mkdirs();
        }
        File picturMediaFile = new File(picturMediaPath);
        if (!picturMediaFile.exists() && !picturMediaFile.isDirectory()) {
            picturMediaFile.mkdirs();
        }
    }

    public static void main(String[] args) {
        getAudioFromVideo("H:\\download\\test.mp4");
    }
    public static void getAudioFromVideo(String videoResourcesPath) {
        String fileName = videoResourcesPath.substring(videoResourcesPath.lastIndexOf("\\") + 1, videoResourcesPath.lastIndexOf("."));
        List<String> command = new ArrayList<>();
        command.add(ffmpeg);
        command.add("-i");
        command.add(videoResourcesPath);
        command.add(saveMediaPath + fileName + ".mp3");
        commandStart(command);
    }
    public static void commandStart(List<String> command) {
        command.forEach(v -> System.out.print(v + " "));
        System.out.println();
        System.out.println();
        ProcessBuilder builder = new ProcessBuilder();
        //正常信息和错误信息合并输出
        builder.redirectErrorStream(true);
        builder.command(command);
        //开始执行命令
        Process process = null;
        try {
            process = builder.start();
            //如果你想获取到执行完后的信息，那么下面的代码也是需要的
            String line = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}