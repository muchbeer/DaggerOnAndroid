package raum.muchbeer.daggeronandroid.views.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AuthSecurity<T> {


    @NonNull
    public final AuthStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public AuthSecurity(@NonNull AuthStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> AuthSecurity<T> authenticated (@Nullable T data) {
        return new AuthSecurity<>(AuthStatus.AUTHENTICATED, data, null);
    }

    public static <T> AuthSecurity<T> error(@NonNull String msg, @Nullable T data) {
        return new AuthSecurity<>(AuthStatus.ERROR, data, msg);
    }

    public static <T> AuthSecurity<T> loading(@Nullable T data) {
        return new AuthSecurity<>(AuthStatus.LOADING, data, null);
    }

    public static <T> AuthSecurity<T> logout () {
        return new AuthSecurity<>(AuthStatus.NOT_AUTHENTICATED, null, null);
    }

    public enum AuthStatus { AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED}

}
