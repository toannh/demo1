package vn.chodientu.entity.data;

import java.io.Serializable;
import java.util.List;
import vn.chodientu.entity.enu.SellerPolicyType;

/**
 *
 * @author Vu Van Thanh
 */
public class SellerPolicy implements Serializable {

    private SellerPolicyType type;
    private List<String> policy;
    private String description;

    public SellerPolicyType getType() {
        return type;
    }

    public void setType(SellerPolicyType type) {
        this.type = type;
    }

    public List<String> getPolicy() {
        return policy;
    }

    public void setPolicy(List<String> policy) {
        this.policy = policy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
