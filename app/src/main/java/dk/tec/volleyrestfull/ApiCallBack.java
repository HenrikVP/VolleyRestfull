package dk.tec.volleyrestfull;

public interface ApiCallBack<T> {
    void onSucces(T result);
    void onError(String error);
}
