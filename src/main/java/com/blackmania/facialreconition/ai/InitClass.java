package com.blackmania.facialreconition.ai;

import org.opencv.core.*;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;

public class InitClass {

    public static int compareFaces(String filepath_origin, String filepath_comparison){
        int retVal = 0;
        long startTime = System.currentTimeMillis();

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat img1 = Imgcodecs.imread(filepath_origin, Imgcodecs.IMREAD_COLOR);
        Mat img2 = Imgcodecs.imread(filepath_comparison, Imgcodecs.IMREAD_COLOR);

        MatOfKeyPoint keyPoints1 = new MatOfKeyPoint();
        MatOfKeyPoint keyPoints2 = new MatOfKeyPoint();
        Mat descriptors1 = new Mat();
        Mat descriptors2 = new Mat();

        FeatureDetector detector = FeatureDetector.create(FeatureDetector.ORB);
        DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.ORB);

        detector.detect(img1, keyPoints1);
        detector.detect(img2, keyPoints2);

        extractor.compute(img1, keyPoints1, descriptors1);
        extractor.compute(img2, keyPoints2, descriptors2);

        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);

        MatOfDMatch matches = new MatOfDMatch();

        if (descriptors2.cols() == descriptors1.cols()) {
            matcher.match(descriptors1, descriptors2, matches);

            DMatch[] match = matches.toArray();
            double max_dist = 0;
            double min_dist = 100;

            for (int i = 0; i < descriptors1.rows(); i++) {
                double dist = match[i].distance;
                if (dist < min_dist) min_dist = dist;
                if (dist > max_dist) max_dist = dist;
            }
            System.out.println("max_dist=" + max_dist + ", min_dist=" + min_dist);

            for (int i = 0; i < descriptors1.rows(); i++) {
                if (match[i].distance <= 10) {
                    retVal++;
                }
            }
            System.out.println("matching count=" + retVal);
        }

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("estimatedTime=" + estimatedTime + "ms");
        return retVal;
    }

}
