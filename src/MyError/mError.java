package MyError;

public class mError {

    int errornum;
    String mes;

    public mError() {
        errornum = 0;
        mes = "";
    }

    public int getErrornum() {
        return errornum;
    }

    public void setErrornum(int errornum) {
        this.errornum = errornum;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
