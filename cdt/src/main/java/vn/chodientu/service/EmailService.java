package vn.chodientu.service;

import com.floreysoft.jmte.Engine;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.EmailOutbox;
import vn.chodientu.entity.enu.EmailOutboxType;
import vn.chodientu.entity.input.EmailOutboxSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.EmailOutboxRepository;

/**
 * @since May 16, 2014
 * @author Phu
 */
@Service
public class EmailService {

    @Autowired
    private ServletContext context;
    @Autowired
    private EmailOutboxRepository emailOutboxRepository;
    @Autowired
    private JavaMailSender mailSender;

    private final String baseUrl = "http://chodientu.vn";
    private final String imageUrl = baseUrl + "/mailtpl/images";

    @Scheduled(fixedDelay = 60000)
    public void doSend() {
        while (true) {
            EmailOutbox out = emailOutboxRepository.getForSend();
            if (out != null) {
                processSendMail(out);
            } else {
                break;
            }
        }
    }

    @Async
    private void processSendMail(EmailOutbox out) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "UTF8");

            helper.setFrom(out.getFrom(), out.getFromName());
            helper.setTo(out.getTo());
            helper.setSubject(out.getSubject());
            helper.setText(out.getBody(), true);

            mailSender.send(message);

            out.setSuccess(true);
            emailOutboxRepository.save(out);
        } catch (Exception ex) {
            out.setSuccess(false);
            out.setResponse(ex.getMessage());
            emailOutboxRepository.save(out);
        }
    }

    /**
     * Gửi email
     *
     * @param type
     * @param to
     * @param subject
     * @param template
     * @param data
     * @throws Exception
     */
    public void send(EmailOutboxType type, String to, String subject, String template, Map<String, Object> data) throws Exception {
        String from = "noreply@chodientu.vn";
        String fromName = "Chợ Điện Tử";

        String body = render(template, data);
        EmailOutbox email = new EmailOutbox();
        email.setFrom(from);
        email.setFromName(fromName);
        email.setTo(to);
        email.setType(type);
        email.setTime(System.currentTimeMillis());
        email.setSubject(subject);
        email.setBody(body);

        emailOutboxRepository.save(email);
    }

    public String render(String template, Map<String, Object> data) throws Exception {
        data.put("baseUrl", baseUrl);
        data.put("imageUrl", imageUrl);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(context.getRealPath("/mailtpl/" + template + ".tpl")), "UTF8"))) {
            StringBuilder content = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            Engine engine = new Engine();
            return engine.transform(content.toString(), data);
        }
    }

    /**
     * Tìm kiếm email trong trang tất cả email
     *
     * @param search
     * @return
     */
    public DataPage<EmailOutbox> search(EmailOutboxSearch search) {
        Criteria criteria = new Criteria();

        if (search.getSuccess() > 0) {
            if (search.getSuccess() == 1) {
                criteria.and("success").is(true);
            } else {
                criteria.and("success").is(false);
            }
        }

        if (search.getSent() > 0) {
            if (search.getSent() == 1) {
                criteria.and("sent").is(true);
            } else {
                criteria.and("sent").is(false);
            }
        }

        if (search.getFrom() != null && !search.getFrom().equals("")) {
            criteria.and("from").is(search.getFrom());
        }
        if (search.getTo() != null && !search.getTo().equals("")) {
            criteria.and("to").is(search.getTo());
        }
        if (search.getType() > 0) {
            switch (search.getType()) {
                case 1:
                    criteria.and("type").is(EmailOutboxType.VERIFY);
                    break;
                case 2:
                    criteria.and("type").is(EmailOutboxType.RESETPASSWORD);
                    break;
                case 3:
                    criteria.and("type").is(EmailOutboxType.NEWPASSWORD);
                    break;
            }
        }

        if (search.getTimeFrom() > 0 && search.getTimeTo() > 0) {
            criteria.and("time").gte(search.getTimeFrom()).lt(search.getTimeTo());
        } else if (search.getTimeFrom() > 0) {
            criteria.and("time").gte(search.getTimeFrom());
        } else if (search.getTimeTo() > 0) {
            criteria.and("time").lt(search.getTimeTo());
        }

        if (search.getSentTimeFrom() > 0 && search.getSentTimeTo() > 0) {
            criteria.and("sentTime").gte(search.getSentTimeFrom()).lt(search.getSentTimeTo());
        } else if (search.getSentTimeFrom() > 0) {
            criteria.and("sentTime").gte(search.getSentTimeFrom());
        } else if (search.getSentTimeTo() > 0) {
            criteria.and("sentTime").lt(search.getSentTimeTo());
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "sentTime"));
        query.skip(search.getPageIndex() * search.getPageSize()).limit(search.getPageSize());

        DataPage<EmailOutbox> page = new DataPage<>();
        page.setPageSize(search.getPageSize());
        page.setPageIndex(search.getPageIndex());
        if (page.getPageSize() <= 0) {
            page.setPageSize(5);
        }
        page.setDataCount(emailOutboxRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }

        page.setData(emailOutboxRepository.find(query));
        return page;
    }

    /**
     * Yêu cầu gửi lại
     *
     * @param id
     * @return
     */
    public Response reSend(String id) {
        EmailOutbox emailOutBox = emailOutboxRepository.find(id);
        if (emailOutBox == null) {
            return new Response(false, "Không tìm thấy email yêu cầu");
        }
        emailOutBox.setSent(false);
        emailOutBox.setSuccess(false);
        emailOutBox.setTime(new Date().getTime());
        emailOutboxRepository.save(emailOutBox);
        return new Response(true, "Yêu cầu gửi lại đã được cập nhật");
    }

}
