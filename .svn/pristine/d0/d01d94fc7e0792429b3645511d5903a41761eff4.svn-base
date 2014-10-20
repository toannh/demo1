/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.HomeBanner;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.HomeBannerForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.HomeBannerRepository;

/**
 *
 * @author toannh
 */
@Service
public class HomeBannerService {

    @Autowired
    private HomeBannerRepository homebannerRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Validator validator;

    /**
     * Save home banner obj to db
     *
     * @param homebannerForm
     * @return
     */
    public Response save(HomeBannerForm homebannerForm) {
        Map<String, String> errors = validator.validate(homebannerForm);
        if (homebannerForm.getId() == null || "".equals(homebannerForm.getId())) {
            if (homebannerForm.getImage() == null || homebannerForm.getImage().getSize() == 0) {
                errors.put("photoCover", "Bạn chưa chọn ảnh");
            }
        }
        if (errors.isEmpty()) {
            HomeBanner homebanner = null;
            if (homebannerForm.getId() != null && !"".equals(homebannerForm.getId())) {
                HomeBanner hb = homebannerRepository.find(homebannerForm.getId());
                if (hb == null) {
                    return new Response(true, "Homebanner isnot exits!");
                }
                homebanner = hb;
            } else {
                homebanner = new HomeBanner();
                homebanner.setId(homebannerRepository.genId());
            }
            homebanner.setName(homebannerForm.getName());
            homebanner.setPosition(homebannerForm.getPosition());
            homebanner.setUrl(homebannerForm.getUrl());
            homebanner.setActive(homebannerForm.isActive());
            homebannerRepository.save(homebanner);

            if (!homebannerForm.getImage().isEmpty()) {
                imageService.delete(ImageType.HOME_BANNER, homebanner.getId());
                imageService.upload(homebannerForm.getImage(), ImageType.HOME_BANNER, homebanner.getId());

            }

            return new Response(true, "Homebanner added/edited successfully!", homebanner);
        }
        return new Response(false, "Home banner add failed! " + errors);
    }

    /**
     * Return home banner object by id
     *
     * @param id
     * @return
     */
    public Response get(String id) {
        HomeBanner homebanner = homebannerRepository.find(id);
        if (homebanner != null) {
            List<String> images = imageService.get(ImageType.HOME_BANNER, id);
            if (images != null && !images.isEmpty()) {
                homebanner.setImage(imageService.getUrl(images.get(0)).getUrl());
            }
            return new Response(true, "data", homebanner);
        }

        return new Response(false, "Cound not found by id" + id);
    }

    /**
     * Return list all home banner
     *
     * @return
     */
    public List<HomeBanner> getHomeBanner() {
        return homebannerRepository.getHomeBanner();
    }

    /**
     *
     * @param id
     * @return
     */
    public Response changeStatus(String id) {
        HomeBanner hb = homebannerRepository.find(id);
        if (hb == null) {
            return new Response(false, "Banner isn't exits!");
        }
        hb.setActive(!hb.isActive());
        homebannerRepository.save(hb);
        return new Response(true, "Banner changed status!", hb);
    }

    /**
     * Delete home banner object by id
     *
     * @param id
     * @return
     */
    public Response delete(String id) {
        HomeBanner homebanner = homebannerRepository.find(id);
        if (homebanner != null) {
            imageService.delete(ImageType.HOME_BANNER, id);
        }
        homebannerRepository.delete(homebanner);
        return new Response(true, "Banner " + id + " deleted successfully!");
    }
     /**
     * Lấy banner ra ngoài trang chủ theo active=true và oderby pisition
     *
     * @return
     */
    @Cacheable(value = "buffercache", key = "'HomeBanner'")
    public List<HomeBanner> list() {
        return homebannerRepository.getAll();
    }
}
