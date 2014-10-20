package vn.chodientu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.output.Response;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.data.CategoryAliasTopic;
import vn.chodientu.entity.db.CategoryAlias;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.CategoryAliasForm;
import vn.chodientu.entity.form.CategoryAliasTopicForm;
import vn.chodientu.repository.CategoryAliasRepository;
import vn.chodientu.repository.CategoryRepository;
import vn.chodientu.repository.ManufacturerRepository;

@Service
public class CategoryAliasService {

    @Autowired
    private CategoryAliasRepository categoryAliasRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Validator validator;

    /**
     * Thêm mới
     *
     * @param aliasForm
     * @return
     */
    public Response add(CategoryAliasForm aliasForm) {
        Map<String, String> errors = validator.validate(aliasForm);
        if (aliasForm.getCategoryId() != null && aliasForm.getCategoryId().trim().endsWith("")) {
            if (!categoryRepository.exists(aliasForm.getCategoryId())) {
                errors.put("categoryId", "Danh mục không tồn tại");
            }
        }
        if (aliasForm.getImage() != null && aliasForm.getImage().getSize() <= 0) {
            errors.put("image", "Ảnh banner không được để trống");
        }
        if (aliasForm.getImage1() != null && aliasForm.getImage1().getSize() <= 0) {
            errors.put("image1", "Ảnh banner không được để trống");
        }
        if (aliasForm.getImage2() != null && aliasForm.getImage2().getSize() <= 0) {
            errors.put("image2", "Ảnh banner không được để trống");
        }
        if (aliasForm.getImage3() != null && aliasForm.getImage3().getSize() <= 0) {
            errors.put("image3", "Ảnh banner không được để trống");
        }

        if (errors.isEmpty()) {
            CategoryAlias alias = new CategoryAlias();
            alias.setId(categoryAliasRepository.genId());
            alias.setActive(aliasForm.isActive());
            alias.setTitle(aliasForm.getTitle());
            alias.setSubTitle(aliasForm.getSubTitle());
            alias.setBannerUrl(aliasForm.getBannerUrl());
            alias.setBannerUrl1(aliasForm.getBannerUrl1());
            alias.setBannerUrl2(aliasForm.getBannerUrl2());
            alias.setBannerUrl3(aliasForm.getBannerUrl3());
            alias.setCategoryId(aliasForm.getCategoryId());
            alias.setPosition(1);
            categoryAliasRepository.save(alias);

            imageService.upload(aliasForm.getImage(), ImageType.CATEGORY_ALIAS_BANNER, alias.getId());
            imageService.upload(aliasForm.getImage1(), ImageType.CATEGORY_ALIAS_BANNER, alias.getId().concat("1"));
            imageService.upload(aliasForm.getImage2(), ImageType.CATEGORY_ALIAS_BANNER, alias.getId().concat("2"));
            imageService.upload(aliasForm.getImage3(), ImageType.CATEGORY_ALIAS_BANNER, alias.getId().concat("3"));

            return new Response(true, "Thêm alias thành công", alias);
        }
        return new Response(false, "Thêm alias thất bại", errors);
    }

    /**
     * Sửa chi tiết
     *
     * @param aliasForm
     * @return
     * @throws java.lang.Exception
     */
    public Response edit(CategoryAliasForm aliasForm) throws Exception {
        Map<String, String> errors = validator.validate(aliasForm);
        CategoryAlias alias = categoryAliasRepository.find(aliasForm.getId());
        if (alias == null) {
            throw new Exception("Alias không tồn tại");
        }
        if (aliasForm.getCategoryId() != null && aliasForm.getCategoryId().trim().endsWith("")) {
            if (!categoryRepository.exists(aliasForm.getCategoryId())) {
                errors.put("categoryId", "Danh mục không tồn tại");
            }
        }
        if (errors.isEmpty()) {
            alias.setTitle(aliasForm.getTitle());
            alias.setActive(aliasForm.isActive());
            alias.setSubTitle(aliasForm.getSubTitle());
            alias.setBannerUrl(aliasForm.getBannerUrl());
            alias.setBannerUrl1(aliasForm.getBannerUrl1());
            alias.setBannerUrl2(aliasForm.getBannerUrl2());
            alias.setBannerUrl3(aliasForm.getBannerUrl3());
            alias.setCategoryId(aliasForm.getCategoryId());
            categoryAliasRepository.save(alias);
            if (aliasForm.getImage() != null && aliasForm.getImage().getSize() > 0) {
                imageService.delete(ImageType.CATEGORY_ALIAS_BANNER, alias.getId());
                imageService.upload(aliasForm.getImage(), ImageType.CATEGORY_ALIAS_BANNER, alias.getId());
            }
            if (aliasForm.getImage1() != null && aliasForm.getImage1().getSize() > 0) {
                imageService.delete(ImageType.CATEGORY_ALIAS_BANNER, alias.getId().concat("1"));
                imageService.upload(aliasForm.getImage1(), ImageType.CATEGORY_ALIAS_BANNER, alias.getId().concat("1"));
            }
            if (aliasForm.getImage2() != null && aliasForm.getImage2().getSize() > 0) {
                imageService.delete(ImageType.CATEGORY_ALIAS_BANNER, alias.getId().concat("2"));
                imageService.upload(aliasForm.getImage2(), ImageType.CATEGORY_ALIAS_BANNER, alias.getId().concat("2"));
            }
            if (aliasForm.getImage3() != null && aliasForm.getImage3().getSize() > 0) {
                imageService.delete(ImageType.CATEGORY_ALIAS_BANNER, alias.getId().concat("3"));
                imageService.upload(aliasForm.getImage3(), ImageType.CATEGORY_ALIAS_BANNER, alias.getId().concat("3"));
            }
            return new Response(true, "Sửa alias thành công", alias);
        }
        return new Response(false, "Sửa alias thất bại", errors);
    }

    /**
     * Sửa topics trong alias
     *
     * @param topicForm
     * @return
     * @throws java.lang.Exception
     */
    public Response editTopics(CategoryAliasTopicForm topicForm) throws Exception {
        Map<String, String> errors = new HashMap<>();
        CategoryAlias alias = categoryAliasRepository.find(topicForm.getId());
        List<CategoryAliasTopic> topics = new ArrayList<>();
        if (alias == null) {
            throw new Exception("Alias không tồn tại");
        }

        List<String> topicImage1 = imageService.get(ImageType.CATEGORY_ALIAS_TOPIC, alias.getId().concat("1"));
        if ((topicForm.getImage1() != null && topicForm.getImage1().getSize() > 0) || (topicImage1 != null && !topicImage1.isEmpty())) {
            if (topicForm.getTitle1() == null || topicForm.getTitle1().trim().equals("")) {
                errors.put("title1", "Tiêu đề topic không được để trống");
            } else if (topicForm.getTitle1().length() > 70) {
                errors.put("title1", "Tiêu đề topic không được quá 70 ký tự");
            }
            if (topicForm.getUrl1() == null || topicForm.getUrl1().trim().equals("")) {
                errors.put("url1", "Url topic không được để trống");
            }
        } else {
            if ((topicForm.getTitle1() != null && !topicForm.getTitle1().trim().equals(""))
                    || (topicForm.getUrl1() != null && !topicForm.getUrl1().trim().equals(""))) {
                errors.put("image1", "Ảnh của topic không được để trống");
            }
        }
        List<String> topicImage2 = imageService.get(ImageType.CATEGORY_ALIAS_TOPIC, alias.getId().concat("2"));
        if ((topicForm.getImage2() != null && topicForm.getImage2().getSize() > 0) || (topicImage2 != null && !topicImage2.isEmpty())) {
            if (topicForm.getTitle2() == null || topicForm.getTitle2().trim().equals("")) {
                errors.put("title2", "Tiêu đề topic không được để trống");
            } else if (topicForm.getTitle2().length() > 70) {
                errors.put("title2", "Tiêu đề topic không được quá 70 ký tự");
            }
            if (topicForm.getUrl2() == null || topicForm.getUrl1().trim().equals("")) {
                errors.put("url2", "Url topic không được để trống");
            }
        } else {
            if ((topicForm.getTitle2() != null && !topicForm.getTitle2().trim().equals(""))
                    || (topicForm.getUrl2() != null && !topicForm.getUrl2().trim().equals(""))) {
                errors.put("image2", "Ảnh của topic không được để trống");
            }
        }
        List<String> topicImage3 = imageService.get(ImageType.CATEGORY_ALIAS_TOPIC, alias.getId().concat("3"));
        if ((topicForm.getImage3() != null && topicForm.getImage3().getSize() > 0) || (topicImage3 != null && !topicImage3.isEmpty())) {
            if (topicForm.getTitle3() == null || topicForm.getTitle3().trim().equals("")) {
                errors.put("title3", "Tiêu đề topic không được để trống");
            } else if (topicForm.getTitle3().length() > 70) {
                errors.put("title3", "Tiêu đề topic không được quá 70 ký tự");
            }
            if (topicForm.getUrl3() == null || topicForm.getUrl3().trim().equals("")) {
                errors.put("url3", "Url topic không được để trống");
            }
        } else {
            if ((topicForm.getTitle3() != null && !topicForm.getTitle3().trim().equals(""))
                    || (topicForm.getUrl3() != null && !topicForm.getUrl3().trim().equals(""))) {
                errors.put("image3", "Ảnh của topic không được để trống");
            }
        }
        List<String> topicImage4 = imageService.get(ImageType.CATEGORY_ALIAS_TOPIC, alias.getId().concat("4"));
        if ((topicForm.getImage4() != null && topicForm.getImage4().getSize() > 0) || (topicImage4 != null && !topicImage4.isEmpty())) {
            if (topicForm.getTitle4() == null || topicForm.getTitle4().trim().equals("")) {
                errors.put("title4", "Tiêu đề topic không được để trống");
            } else if (topicForm.getTitle4().length() > 70) {
                errors.put("title4", "Tiêu đề topic không được quá 70 ký tự");
            }
            if (topicForm.getUrl4() == null || topicForm.getUrl4().trim().equals("")) {
                errors.put("url4", "Url topic không được để trống");
            }
        } else {
            if ((topicForm.getTitle4() != null && !topicForm.getTitle4().trim().equals(""))
                    || (topicForm.getUrl4() != null && !topicForm.getUrl4().trim().equals(""))) {
                errors.put("image4", "Ảnh của topic không được để trống");
            }
        }
        if (errors.isEmpty()) {
            CategoryAliasTopic topic = new CategoryAliasTopic();
            if (topicForm.getTitle1() != null && !topicForm.getTitle1().trim().equals("")) {
                topic.setPosition(1);
                topic.setTitle(topicForm.getTitle1());
                topic.setUrl(topicForm.getUrl1());
                if (topicForm.getImage1() != null && topicForm.getImage1().getSize() > 0) {
                    imageService.delete(ImageType.CATEGORY_ALIAS_TOPIC, alias.getId().concat("1"));
                    imageService.upload(topicForm.getImage1(), ImageType.CATEGORY_ALIAS_TOPIC, alias.getId().concat("1"));
                }
                topics.add(topic);
            }
            if (topicForm.getTitle2() != null && !topicForm.getTitle2().trim().equals("")) {
                topic = new CategoryAliasTopic();
                topic.setPosition(2);
                topic.setTitle(topicForm.getTitle2());
                topic.setUrl(topicForm.getUrl2());
                if (topicForm.getImage2() != null && topicForm.getImage2().getSize() > 0) {
                    imageService.delete(ImageType.CATEGORY_ALIAS_TOPIC, alias.getId().concat("2"));
                    imageService.upload(topicForm.getImage2(), ImageType.CATEGORY_ALIAS_TOPIC, alias.getId().concat("2"));
                }
                topics.add(topic);
            }
            if (topicForm.getTitle3() != null && !topicForm.getTitle3().trim().equals("")) {
                topic = new CategoryAliasTopic();
                topic.setPosition(3);
                topic.setTitle(topicForm.getTitle3());
                topic.setUrl(topicForm.getUrl3());
                if (topicForm.getImage3() != null && topicForm.getImage3().getSize() > 0) {
                    imageService.delete(ImageType.CATEGORY_ALIAS_TOPIC, alias.getId().concat("3"));
                    imageService.upload(topicForm.getImage3(), ImageType.CATEGORY_ALIAS_TOPIC, alias.getId().concat("3"));
                }
                topics.add(topic);
            }
            if (topicForm.getTitle4() != null && !topicForm.getTitle4().trim().equals("")) {
                topic = new CategoryAliasTopic();
                topic.setPosition(4);
                topic.setTitle(topicForm.getTitle4());
                topic.setUrl(topicForm.getUrl4());
                if (topicForm.getImage4() != null && topicForm.getImage4().getSize() > 0) {
                    imageService.delete(ImageType.CATEGORY_ALIAS_TOPIC, alias.getId().concat("4"));
                    imageService.upload(topicForm.getImage4(), ImageType.CATEGORY_ALIAS_TOPIC, alias.getId().concat("4"));
                }
                topics.add(topic);
            }
            alias.setTopics(topics);
            categoryAliasRepository.save(alias);

            return new Response(true, "Sửa topics thành công", alias);
        }
        return new Response(false, "Sửa topics thất bại", errors);
    }

    /**
     * Sửa topics trong alias
     *
     * @param aliasForm
     * @return
     * @throws java.lang.Exception
     */
    public Response editManufacturer(CategoryAliasForm aliasForm) throws Exception {
        Map<String, String> errors = new HashMap<>();
        CategoryAlias alias = categoryAliasRepository.find(aliasForm.getId());
        if (alias == null) {
            throw new Exception("Alias không tồn tại");
        }
        List<String> manufIds = new ArrayList<>();
        if (aliasForm.getManufacturerIds() != null && !aliasForm.getManufacturerIds().isEmpty()) {
            for (int i = 0; i < aliasForm.getManufacturerIds().size(); i++) {
                if (manufacturerRepository.exists(aliasForm.getManufacturerIds().get(i))) {
                    manufIds.add(aliasForm.getManufacturerIds().get(i));
                }
            }
        }
        if (errors.isEmpty()) {
            alias.setManufacturerIds(manufIds);
            categoryAliasRepository.save(alias);

            return new Response(true, "Sửa danh sách thương hiệu thành công", alias);
        }
        return new Response(false, "Sửa danh sách thương hiệu thất bại", errors);
    }

    /**
     * Tìm kiếm phân trang
     *
     * @param active
     * @return
     */
    public List<CategoryAlias> getAll(int active) {
        List<CategoryAlias> all = categoryAliasRepository.getAll(active);
        for (CategoryAlias categoryAlias : all) {
            List<String> images = imageService.get(ImageType.CATEGORY_ALIAS_BANNER, categoryAlias.getId());
            if (images != null && !images.isEmpty()) {
                categoryAlias.setImage(imageService.getUrl(images.get(0)).compress(100).getUrl()); // .thumbnail(618, 283, "outbound")
            }
            List<String> images1 = imageService.get(ImageType.CATEGORY_ALIAS_BANNER, categoryAlias.getId().concat("1"));
            if (images1 != null && !images1.isEmpty()) {
                categoryAlias.setImage1(imageService.getUrl(images1.get(0)).compress(100).getUrl());
            }
            List<String> images2 = imageService.get(ImageType.CATEGORY_ALIAS_BANNER, categoryAlias.getId().concat("2"));
            if (images2 != null && !images2.isEmpty()) {
                categoryAlias.setImage2(imageService.getUrl(images2.get(0)).compress(100).getUrl());
            }
            List<String> images3 = imageService.get(ImageType.CATEGORY_ALIAS_BANNER, categoryAlias.getId().concat("3"));
            if (images3 != null && !images3.isEmpty()) {
                categoryAlias.setImage3(imageService.getUrl(images3.get(0)).compress(100).getUrl());
            }
            if (categoryAlias.getTopics() != null && !categoryAlias.getTopics().isEmpty()) {
                for (CategoryAliasTopic topic : categoryAlias.getTopics()) {
                    List<String> topicImages = imageService.get(ImageType.CATEGORY_ALIAS_TOPIC, categoryAlias.getId().concat(Integer.toString(topic.getPosition())));
                    if (topicImages != null && !topicImages.isEmpty()) {
                        topic.setImage(imageService.getUrl(topicImages.get(0)).thumbnail(74, 74, "outbound").compress(100).getUrl());
                    }
                }
            }
        }
        return all;
    }

    /**
     * Lấy chi tiết 1 alias
     *
     * @param id
     * @return
     * @throws Exception
     */
    public CategoryAlias get(String id) throws Exception {
        if (!categoryAliasRepository.exists(id)) {
            throw new Exception("Alias không tồn tại");
        }
        CategoryAlias categoryAlias = categoryAliasRepository.find(id);
        List<String> images = imageService.get(ImageType.CATEGORY_ALIAS_BANNER, categoryAlias.getId());
        if (images != null && !images.isEmpty()) {
            categoryAlias.setImage(imageService.getUrl(images.get(0)).thumbnail(100, 100, "outbound").compress(100).getUrl());
        }
        List<String> images1 = imageService.get(ImageType.CATEGORY_ALIAS_BANNER, categoryAlias.getId().concat("1"));
        if (images1 != null && !images1.isEmpty()) {
            categoryAlias.setImage1(imageService.getUrl(images1.get(0)).thumbnail(100, 100, "outbound").compress(100).getUrl());
        }
        List<String> images2 = imageService.get(ImageType.CATEGORY_ALIAS_BANNER, categoryAlias.getId().concat("2"));
        if (images2 != null && !images2.isEmpty()) {
            categoryAlias.setImage2(imageService.getUrl(images2.get(0)).thumbnail(100, 100, "outbound").compress(100).getUrl());
        }
        List<String> images3 = imageService.get(ImageType.CATEGORY_ALIAS_BANNER, categoryAlias.getId().concat("3"));
        if (images3 != null && !images3.isEmpty()) {
            categoryAlias.setImage3(imageService.getUrl(images3.get(0)).thumbnail(100, 100, "outbound").compress(100).getUrl());
        }
        if (categoryAlias.getTopics() != null && !categoryAlias.getTopics().isEmpty()) {
            for (CategoryAliasTopic topic : categoryAlias.getTopics()) {
                List<String> topicImages = imageService.get(ImageType.CATEGORY_ALIAS_TOPIC, categoryAlias.getId().concat(Integer.toString(topic.getPosition())));
                if (topicImages != null && !topicImages.isEmpty()) {
                    topic.setImage(imageService.getUrl(topicImages.get(0)).thumbnail(50, 50, "outbound").compress(100).getUrl());
                }
            }
        }
        return categoryAlias;
    }
    public Response changePosition(String aliasId,int position) throws Exception{
        if (!categoryAliasRepository.exists(aliasId)) {
            throw new Exception("Alias không tồn tại");
        }
        if(position<0){
            throw new Exception("Giá trị phải >=0");
        }
        CategoryAlias categoryAlias = categoryAliasRepository.find(aliasId);
        categoryAlias.setPosition(position);
        categoryAliasRepository.save(categoryAlias);
        return new Response(true, "Sửa vị trí thành công");
    }

}
