package raum.muchbeer.daggeronandroid.views.main.users;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserState<T> {

    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;

    public UserState(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }


    public static <T> UserState<T> success (@Nullable T data) {
        return new UserState<T>(Status.SUCCESS, data, null);
    }

    public static <T> UserState<T> error(@NonNull String msg, @Nullable T data) {
        return new UserState<T>(Status.ERROR, data, msg);
    }

    public static <T> UserState<T> loading(@Nullable T data) {
        return new UserState<T>(Status.LOADING, data, null);
    }

    public enum Status { SUCCESS, ERROR, LOADING}
}
