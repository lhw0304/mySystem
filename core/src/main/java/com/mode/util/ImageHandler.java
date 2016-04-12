package com.mode.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.mode.config.BaseConfig;

public class ImageHandler {

    // The default height and width of each chunk in the 3x3 grid image.
    private static final int DEFAULT_CHUNK_HEIGHT = 200;
    private static final int DEFAULT_CHUNK_WIDTH = 200;
    // The default columns and rows of the 3x3 grid images
    private static final int NUM_CHUNKS_PER_ROW = 3;
    private static final int NUM_CHUNKS_PER_COL = 3;

    // The default handler for Aliyun OSS storage.
    private final static OSSHandler ossHandler = new OSSHandler();

    /**
     * Put item image to OSS
     *
     * @param originalImage The image object
     * @param folder        The dest folder
     * @param x             The X coordinate of the upper-left corner of the specified
     *                      rectangular region
     * @param y             The Y coordinate of the upper-left corner of the specified
     *                      rectangular region
     * @param width         The width to scale
     * @param height        The height to scale
     * @param format        The format of the image, 'jpg', 'png', 'gif' and so on
     * @return Url of the image
     */
    public String putImage(BufferedImage originalImage, String folder, Integer x, Integer y,
                           Integer width, Integer height, String format) {
        /* Resize the original image into the size we want. */
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage
                .getType();

        // Resize and crop the original image with the predefined width/height for images.
        BufferedImage resizedImage = resizeBufferedImage(originalImage, x, y, width, height, type);

        /* Put the image to OSS and return. */
        return ossHandler.putImageToOSS(resizedImage, folder, format);
    }


    /**
     * Put image to OSS
     *
     * @param object
     * @param folder
     * @param format
     * @return
     */
    public String putImage(InputStream object, String folder, String format) {
        return ossHandler.putImageToOSS(object, folder, format);
    }

    /**
     * Generate a grid image for this collection and put it to OSS
     *
     * @param images The list of images we want to merge
     * @return BufferedImage
     */
    public String putGridImage(List<Image> images) {

        /*
         * Initialize new buffered image which will be used to store the new
         * generated image
         */
        BufferedImage gridImage = generateGridImage(images, DEFAULT_CHUNK_WIDTH,
                DEFAULT_CHUNK_HEIGHT, NUM_CHUNKS_PER_COL, NUM_CHUNKS_PER_ROW);

        /* Put grid image to oss. */
        return ossHandler.putImageToOSS(gridImage, BaseConfig.FOLDER_COLLECTION, getFormat(null));
    }

    /**
     * Get the image URL.
     *
     * @param image  The MultipartFile for the image object
     * @param x      The X coordinate of the upper-left corner of the specified rectangular region
     * @param y      The Y coordinate of the upper-left corner of the specified rectangular region
     * @param width  The width to scale
     * @param height The height to scale
     * @return image url or null
     */
    public String getImageURL(MultipartFile image, String folder, Integer x, Integer y, Integer
            width, Integer height) {
        // Transfer MultipartFile to image.
        try (InputStream is = new ByteArrayInputStream(image.getBytes())) {
            BufferedImage originalImage = ImageIO.read(is);
            if (folder.equalsIgnoreCase(BaseConfig.FOLDER_MODELOOK)
                    || folder.equalsIgnoreCase(BaseConfig.FOLDER_AVATAR)
                    || folder.equalsIgnoreCase(BaseConfig.FOLDER_STYLIST_AVATAR)
                    || folder.equalsIgnoreCase(BaseConfig.FOLDER_REDEEM)
                    || folder.equalsIgnoreCase(BaseConfig.FOLDER_LUCKYDRAW)
                    || folder.equalsIgnoreCase(BaseConfig.FOLDER_NOTIFICATION)
                    || folder.equalsIgnoreCase(BaseConfig.FOLDER_ADV)
                    || folder.equalsIgnoreCase(BaseConfig.FOLDER_HEADLINE)
                    || folder.equalsIgnoreCase(BaseConfig.FOLDER_COVER_IMAGE)) {
                width = originalImage.getWidth();
                height = originalImage.getHeight();
            }

            String imageName = image.getOriginalFilename();
            String format = getFormat(imageName);

            if (format.equalsIgnoreCase("gif")) {
                return putImage(image.getInputStream(), folder, format);
            } else {
                return putImage(originalImage, folder, x, y, width, height, format);
            }

        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Generate a new grid collection image
     *
     * @param images      a list of the images
     * @param chunkWidth  the width of each chunk
     * @param chunkHeight the height of each chunk
     * @param cols        number of columns
     * @param rows        number of rows
     * @return the grid image object
     */
    private BufferedImage generateGridImage(List<Image> images, int chunkWidth, int chunkHeight,
                                            int cols, int rows) {

        /*
         * Initialize new buffered image which will be used to store the new
         * generated image
         */
        BufferedImage gridImage = new BufferedImage(chunkWidth * cols, chunkHeight * rows,
                BufferedImage.TYPE_INT_RGB);

        /* Get image array and write them to the new image */
        List<BufferedImage> bimages = toListScaledBufferedImage(images, chunkWidth, chunkHeight);
        int[][] imageArrays = new int[bimages.size()][];
        for (int i = 0; i < bimages.size(); i++) {
            imageArrays[i] = new int[chunkWidth * chunkHeight];
            imageArrays[i] = bimages.get(i).getRGB(0, 0, chunkWidth, chunkHeight, imageArrays[i],
                    0, chunkWidth);
        }

        int count = 0;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                gridImage.setRGB(chunkWidth * i, chunkHeight * j, chunkWidth, chunkHeight,
                        imageArrays[count], 0, chunkWidth);
                count++;
            }
        }

        return gridImage;
    }

    /**
     * Convert a list of Images to BufferedImages with specific scaled width and
     * height.
     *
     * @param images The images
     * @param width  The width to scale
     * @param height The height to scale
     * @return the list of resized images
     */
    private List<BufferedImage> toListScaledBufferedImage(List<Image> images, Integer width, Integer
            height) {
        List<BufferedImage> bufferedImages = new ArrayList<>();

        for (Image image : images) {
            BufferedImage bi = toBufferedImage(image);
            int type = bi.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bi.getType();
            bi = resizedBufferedImage(bi, width, height, type);
            bufferedImages.add(bi);
        }
        return bufferedImages;
    }

    /**
     * Resize the originalImage into a image with specific width and height.
     *
     * @param originalImage
     * @param width
     * @param height
     * @param type
     * @return
     */
    private BufferedImage resizedBufferedImage(BufferedImage originalImage, Integer width,
                                               Integer height, Integer type) {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

    /**
     * Resize the originalImage into a image with specific width and height.
     *
     * @param originalImage The image with original size
     * @param x             The X coordinate of the upper-left corner of the specified
     *                      rectangular region
     * @param y             The Y coordinate of the upper-left corner of the specified
     *                      rectangular region
     * @param width         The width to scale
     * @param height        The height to scale
     * @param type          The image type
     * @return the resized image
     */
    private BufferedImage resizeBufferedImage(BufferedImage originalImage, Integer x, Integer y,
                                              Integer width, Integer height, Integer type) {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        BufferedImage cropperImage = originalImage.getSubimage(x, y, width, height);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(cropperImage, 0, 0, width, height, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

    /**
     * Convert Image to BufferedImage.
     *
     * @param img The source image file
     * @return BufferedImage
     */
    private BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    private String getFormat(String imageName) {
        String temp = new String(imageName);
        temp.toLowerCase();

        if (temp.endsWith(".png"))
            return "png";

        if (temp.endsWith(".gif"))
            return "gif";

        return "jpg";
    }

}