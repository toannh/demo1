package vn.chodientu.component;

import java.io.File;
import java.net.URI;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import vn.chodientu.component.imboclient.ImboClient;
import vn.chodientu.entity.output.Response;

/**
 * @since May 28, 2014
 * @author Phu
 */
public class ImageClient extends ImboClient {

    public ImageClient(String serverUrl, String publicKey, String privateKey) {
        super(serverUrl, publicKey, privateKey);
    }

    public ImageClient(String[] serverUrl, String publicKey, String privateKey) {
        super(serverUrl, publicKey, privateKey);
    }

    /**
     * Download ảnh từ một url
     *
     * @param imageUrl
     * @return
     */
    public Response download(String imageUrl) {
        try {
            vn.chodientu.component.imboclient.Http.Response resp = addImageFromUrl(new URI(imageUrl));
            if (resp.isSuccess()) {
                return new Response(true, "Download ảnh thành công", resp.getImageIdentifier());
            }
            return new Response(false, resp.getImboErrorDescription(), resp.getStatusCode());
        } catch (Exception ex) {
            return new Response(false, "Đăng ảnh thất bại, ảnh của bạn vượt quá 500kb", ex.getMessage());
        }
    }

    public Response upload(MultipartFile images) {
        try {
            File file = File.createTempFile(UUID.randomUUID().toString(), images.getContentType().split("/")[1]);
            images.transferTo(file);
            vn.chodientu.component.imboclient.Http.Response resp = addImage(file);
            if (resp.isSuccess()) {
                return new Response(true, "Upload ảnh thành công", resp.getImageIdentifier());
            }
            return new Response(false, resp.getImboErrorDescription(), resp.getStatusCode());

        } catch (Exception ex) {
            return new Response(false, "Đăng ảnh thất bại, ảnh của bạn vượt quá 500kb", ex.getMessage());
        }
    }
}
