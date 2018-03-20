package semana1.curso3.coursera.pojo;

public class Relationship {

    private String outgoing_status;
    private int code;

    public Relationship() {

    }

    public Relationship(int code) {
        this.code = code;
    }

    public String getOutgoing_status() {
        return outgoing_status;
    }

    public void setOutgoing_status(String outgoing_status) {
        this.outgoing_status = outgoing_status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}