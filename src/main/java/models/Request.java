package models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Request implements Serializable {
    private static final long serialVersionUID = 4859910492998706744L;
    private String requestId;
    private String requestGroup;
    private String requestContent;
}
