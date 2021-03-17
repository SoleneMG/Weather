package main.data.server.models;

public class NetworkResponse<T> {
    public final int code;
    public final T data;

    public NetworkResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    @Override
    public String toString() {
        return "NetworkResponse{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
