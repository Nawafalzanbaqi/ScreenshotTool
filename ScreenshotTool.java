import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

public class ScreenshotTool {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage:");
            System.out.println("java ScreenshotTool <output-folder-path>");
            System.out.println("Example (Windows):");
            System.out.println("java ScreenshotTool C:\\output");
            return;
        }

        String outputDirPath = args[0];

        File outputDir = new File(outputDirPath);
        if (!outputDir.exists()) {
            if (!outputDir.mkdirs()) {
                System.out.println("Failed to create output directory: " + outputDirPath);
                return;
            }
        }

        // Use timestamp for all filenames
        long timestamp = System.currentTimeMillis();
        String baseName = "screenshot_" + timestamp;

        File screenshotFile = new File(outputDir, baseName + ".png");
        File grayFile = new File(outputDir, baseName + "_gray.png");
        File invertedFile = new File(outputDir, baseName + "_inverted.png");

        // ZIP will have SAME name as images
        String zipFilePath = new File(outputDir, baseName + ".zip").getAbsolutePath();

        try {
            // 1) Capture screenshot
            BufferedImage screenshot = takeScreenshot();
            ImageIO.write(screenshot, "png", screenshotFile);
            System.out.println("Screenshot saved: " + screenshotFile.getAbsolutePath());

            // 2) Convert to grayscale
            BufferedImage grayImage = toGrayscale(screenshot);
            ImageIO.write(grayImage, "png", grayFile);
            System.out.println("Grayscale image saved: " + grayFile.getAbsolutePath());

            // 3) Invert grayscale
            BufferedImage invertedImage = invertImage(grayImage);
            ImageIO.write(invertedImage, "png", invertedFile);
            System.out.println("Inverted image saved: " + invertedFile.getAbsolutePath());

            // 4) ZIP all images
            zipFiles(zipFilePath, new File[]{screenshotFile, grayFile, invertedFile});
            System.out.println("ZIP file created: " + zipFilePath);

        } catch (Exception e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }
    }

    private static BufferedImage takeScreenshot() throws AWTException {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        Robot robot = new Robot();
        return robot.createScreenCapture(screenRect);
    }

    private static BufferedImage toGrayscale(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage gray = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = original.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int grayLevel = (int)(0.299 * r + 0.587 * g + 0.114 * b);
                int grayRgb = (grayLevel << 16) | (grayLevel << 8) | grayLevel;
                gray.setRGB(x, y, grayRgb);
            }
        }

        return gray;
    }

    private static BufferedImage invertImage(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage inverted = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = original.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int ir = 255 - r;
                int ig = 255 - g;
                int ib = 255 - b;

                int invertedRgb = (ir << 16) | (ig << 8) | ib;
                inverted.setRGB(x, y, invertedRgb);
            }
        }

        return inverted;
    }

    private static void zipFiles(String zipFilePath, File[] files) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            byte[] buffer = new byte[4096];
            for (File file : files) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(file.getName());
                    zos.putNextEntry(entry);

                    int len;
                    while ((len = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }

                    zos.closeEntry();
                }
            }
        }
    }
}
