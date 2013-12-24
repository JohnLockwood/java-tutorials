package RestfulWebService;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.String;

public class Task {
    private String  description;
    private String  assigned;
    private Integer id;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssigned() {
        return assigned;
    }
    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
