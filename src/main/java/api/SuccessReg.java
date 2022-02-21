package api;

public class SuccessReg {
    private Integer id;
    private String token;

    public SuccessReg constrRegSuccess(Integer id, String token) {
        this.id = id;
        this.token = token;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
