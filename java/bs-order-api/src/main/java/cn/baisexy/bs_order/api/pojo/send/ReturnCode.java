package cn.baisexy.bs_order.api.pojo.send;



public enum ReturnCode {
    SUCCESS(200, "操作成功"),
    FAIL(400, "操作失败"),
    WITHOUTTOKEN(401, "没有token"),

    TOKENNOTLEGAL(402,"token不合法"),
    TOKENEXPIRED(403,"token过期了"),
    DEFAULT_EXCEPTION(500);
    public int statusCode;
    public String msg;

    ReturnCode(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }

    ReturnCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ReturnCode{" +
                "statusCode=" + statusCode +
                ", msg='" + msg + '\'' +
                '}';
    }
}
