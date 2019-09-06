package com.durex.videos.utils;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author gelong
 * @date 2019/9/6 12:16
 */
public class VideosUtil {

    public static void main(String[] args) throws Exception {
        randomGrabberFfmpegImage("C:/Users/durex/Desktop/videos-dev/video/20190827112842.mp4",
                "C:/Users/durex/Desktop/videos-dev/image", "test");
    }

    /**
     * 对视频第一秒进行截图
     * @param filePath 视频路径
     * @param targetFilePath 生成图片的路径
     * @param targetFileName 成图片文件名
     * @throws Exception 异常
     */
    public static void randomGrabberFfmpegImage(String filePath, String targetFilePath, String targetFileName)
            throws Exception {

        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(filePath);
        ff.start();
        String rotate = ff.getVideoMetadata("rotate");
        Frame f;
        int i = 0;
        while (i < 1) {
            f = ff.grabImage();
            if (null != rotate && rotate.length() > 1) {
                OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
                opencv_core.IplImage src = converter.convert(f);
                f = converter.convert(rotate(src, Integer.valueOf(rotate)));
            }
            doExecuteFrame(f, targetFilePath, targetFileName);
            i++;
        }
        ff.stop();
    }

    /**
     * 旋转角度的
     * @param src 源图片
     * @param angle 旋转角度
     * @return IplImage
     */
    private static opencv_core.IplImage rotate(opencv_core.IplImage src, int angle) {
        opencv_core.IplImage img = opencv_core.IplImage.create(src.height(), src.width(), src.depth(), src.nChannels());
        opencv_core.cvTranspose(src, img);
        opencv_core.cvFlip(img, img, angle);
        return img;
    }

    private static void doExecuteFrame(Frame f, String targerFilePath, String targetFileName) {

        if (null == f || null == f.image) {
            return;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        String imageMat = "jpg";
        String fileName = targerFilePath + File.separator + targetFileName + "." + imageMat;
        BufferedImage bi = converter.getBufferedImage(f);
        System.out.println("width:" + bi.getWidth());
        System.out.println("height:" + bi.getHeight());
        File output = new File(fileName);
        try {
            ImageIO.write(bi, imageMat, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
