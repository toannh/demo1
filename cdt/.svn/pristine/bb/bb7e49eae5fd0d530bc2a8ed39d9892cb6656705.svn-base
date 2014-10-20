package vn.chodientu.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.chodientu.component.ImageClient;
import vn.chodientu.component.imboclient.Url.ImageUrl;
import vn.chodientu.entity.db.Image;
import vn.chodientu.entity.db.TmpImage;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.ImageRepository;
import vn.chodientu.repository.TmpImageRepository;

/**
 * @since May 28, 2014
 * @author Phu
 */
@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private TmpImageRepository tmpImageRepository;
    @Autowired
    private ImageClient imageClient;

    /**
     * Xóa ảnh rác
     */
    public void clean() {

    }

    /**
     * Lấy ảnh tmp về
     */
    @Scheduled(fixedDelay = 60000)
    public void getTmp() {
        while (true) {
            TmpImage tmp = tmpImageRepository.getForGet();
            if (tmp == null) {
                break;
            }

            Response resp = download(tmp.getUrl(), tmp.getType(), tmp.getTargetId());
            Logger.getLogger("tmp_image").log(Level.INFO, Thread.currentThread().getName() + " " + resp.getMessage() + " " + tmp.getUrl());
        }
    }

    /**
     * Lưu tạm ảnh tmp hệ thống tự download về sau
     *
     * @param url Link ảnh gốc
     * @param targetId id của đối tượng có ảnh
     * @param type kiểu đối tượng có ảnh
     * @return
     */
    public void addTmp(String url, ImageType type, String targetId) {
        TmpImage img = new TmpImage();
        img.setUrl(url);
        img.setTargetId(targetId);
        img.setType(type);
        tmpImageRepository.save(img);
    }

    /**
     * Download ảnh về
     *
     * @param url Link ảnh gốc
     * @param targetId id của đối tượng có ảnh
     * @param type kiểu đối tượng có ảnh
     * @return
     */
    public Response download(String url, ImageType type, String targetId) {
        Response resp = imageClient.download(url);
        if (resp.isSuccess()) {
            Image img = new Image();
            img.setImageId(resp.getData().toString());
            img.setPosition(imageRepository.getLastPosition(type, targetId));
            img.setTargetId(targetId);
            img.setType(type);
            imageRepository.save(img);
        }
        return resp;
    }

    /**
     * Upload ảnh
     *
     * @param images Ảnh cần upload
     * @param targetId id của đối tượng có ảnh
     * @param type kiểu đối tượng có ảnh
     * @return
     */
    public Response upload(MultipartFile images, ImageType type, String targetId) {
        Response resp = imageClient.upload(images);
        if (resp.isSuccess()) {
            Image img = new Image();
            img.setImageId(resp.getData().toString());
            img.setPosition(imageRepository.getLastPosition(type, targetId));
            img.setTargetId(targetId);
            img.setType(type);
            imageRepository.save(img);
        }
        return resp;
    }

    /**
     * Xóa ảnh theo đối tượng, ảnh vật lý cũng sẽ được xóa luôn nhé ^^
     *
     * @param type
     * @param targetId
     */
    @Async
    public void delete(ImageType type, String targetId) {
        List<Image> images = imageRepository.get(type, targetId);
        for (Image img : images) {
            try {
                imageClient.deleteImage(img.getImageId());
            } catch (IOException ex) {
                Logger.getLogger(ImageService.class.getName()).log(Level.ERROR, null, ex);
            }
        }
        imageRepository.delete(type, targetId);
    }

    /**
     * Xóa ảnh theo id ảnh
     *
     * @param type
     * @param targetId
     * @param imageId
     */
    @Async
    public void deleteById(ImageType type, String targetId, String imageId) {
        imageRepository.delete(type, targetId, imageId);
        if (imageRepository.count(imageId) <= 0) {
            try {
                imageClient.deleteImage(imageId);
            } catch (IOException ex) {
                Logger.getLogger(ImageService.class.getName()).log(Level.ERROR, null, ex);
            }
        }
    }

    /**
     * Xóa ảnh theo url ảnh
     *
     * @param type
     * @param targetId
     * @param imageUrl
     */
    @Async
    public void deleteByUrl(ImageType type, String targetId, String imageUrl) {
        String[] tmp = imageUrl.split("\\?")[0].split("/");
        String imageId = tmp[tmp.length - 1];
        deleteById(type, targetId, imageId);
    }
    
    /**
     * Lấy list id ảnh theo đối tượng
     *
     * @param type
     * @param targetId
     * @return
     */
    public List<String> get(ImageType type, String targetId) {
        List<Image> images = imageRepository.get(type, targetId);
        List<String> imageIds = new ArrayList<>();
        for (Image img : images) {
            imageIds.add(img.getImageId());
        }
        return imageIds;
    }

    /**
     * Lấy list id ảnh theo nhiều đối tượng
     *
     * @param type
     * @param targetIds
     * @return
     */
    public Map<String, List<String>> get(ImageType type, List<String> targetIds) {
        Map<String, List<String>> imageIds = new HashMap<>();
        if (!targetIds.isEmpty()) {
            List<Image> images = imageRepository.get(type, targetIds);

            for (String targetId : targetIds) {
                List<String> imgs = new ArrayList<>();
                for (Image img : images) {
                    if (img.getTargetId().equals(targetId)) {
                        imgs.add(img.getImageId());
                    }
                }
                imageIds.put(targetId, imgs);
            }
        }
        return imageIds;
    }

    /**
     *
     * @param imageId
     * @return
     */
    public ImageUrl getUrl(String imageId) {
        return imageClient.getImageUrl(imageId);
    }

    /**
     * Sửa position của ảnh (sửa ảnh đại diện cho sản phẩm)
     *
     * @param imageId
     * @param targetId
     * @param type
     */
    public void resetFirstImage(String imageId, String targetId, ImageType type) {
        Image get = imageRepository.get(type, targetId, imageId);
        Image firstImg = imageRepository.getFirstImage(type, targetId);
        int position = 0;

        if (get != null) {
            if (firstImg != null) {
                position = firstImg.getPosition();
                firstImg.setPosition(get.getPosition());
                imageRepository.save(firstImg);
            }
            get.setPosition(position);
            imageRepository.save(get);
        }
    }

    /**
     * Lấy ảnh có vị trí nhỏ nhất
     *
     * @param targetId
     * @param imageType
     * @return
     */
    public Image getFirstImage(String targetId, ImageType imageType) {
        return imageRepository.getFirstImage(imageType, targetId);
    }

    /**
     * Lấy ảnh có vị trí lớn nhất(cuối cùng)
     *
     * @param targetId
     * @param imageType
     * @return
     */
    public Image getLastImage(String targetId, ImageType imageType) {
        return imageRepository.getLastImage(imageType, targetId);
    }

    /**
     * Kiểm tra kích cỡ của ảnh
     *
     * @param imageUrl
     * @param files
     * @param minWidth
     * @param minHeight
     * @param checkfull true: check 2 chiều ảnh, false: check ảnh theo 1 chiều
     * @return
     */
    public boolean checkImage(String imageUrl, MultipartFile files, int minWidth, int minHeight, boolean checkfull) {
        try {
            BufferedImage image = null;
            if (imageUrl != null && !imageUrl.equals("")) {
                URL url = new URL(imageUrl);
                image = ImageIO.read(url);
            }
            if (files != null && files.getSize() > 0) {
                image = ImageIO.read(files.getInputStream());
            }
            if (image != null) {
                Integer width = image.getWidth();
                Integer height = image.getHeight();
                if ((!checkfull && (width >= minWidth || height >= minHeight))
                        || (checkfull && width >= minWidth && height >= minHeight)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            return false;
        }
        return false;
    }
}
