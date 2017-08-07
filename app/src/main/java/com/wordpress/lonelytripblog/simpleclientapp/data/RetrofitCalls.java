package com.wordpress.lonelytripblog.simpleclientapp.data;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface for HTTP API.
 */

public interface RetrofitCalls {
    @GET("users")
    Call<List<User>> getAllUsers();
    @GET("albums")
    Call<List<Album>> getAlbumsByUser(@Query("userId") int id);
    @GET("posts")
    Call<List<Post>> getPostsByUser(@Query("userId") int id);
    @GET("albums/{album}/photos")
    Call<List<Photo>> getPhotosByAlbum(@Path("album") int id);
    @FormUrlEncoded
    @POST("posts")
    Call<ResponseBody> postPost(@Field("title") String title,
                                @Field("body") String body,
                                @Field("userId") int id);
    @DELETE("posts/{id}")
    Call<ResponseBody> deletePost(@Path("id") int id);
}
