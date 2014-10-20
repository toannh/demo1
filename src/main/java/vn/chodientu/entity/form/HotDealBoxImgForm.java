package vn.chodientu.entity.form;

import org.springframework.web.multipart.MultipartFile;

/**
 * @since Jun 16, 2014
 * @author Phuongdt
 */
public class HotDealBoxImgForm {

    private String id;
    private MultipartFile images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MultipartFile getImages() {
        return images;
    }

    public void setImages(MultipartFile images) {
        this.images = images;
    }

}
