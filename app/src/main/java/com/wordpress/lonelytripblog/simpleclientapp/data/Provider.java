package com.wordpress.lonelytripblog.simpleclientapp.data;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Model in MVP pattern.
 */

public class Provider implements Repository {

    private static Provider INSTANCE = null;
    private final static String BASE_URL = "http://jsonplaceholder.typicode.com/";
    private RetrofitCalls retrofitInterface;

    public static Provider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Provider();
        }
        return INSTANCE;
    }

    private Provider() {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitCalls.class);
    }

    @Override
    public void getAllUsers(final LoadUsersCallback callback) {
        Call<List<User>> listOfUsersCall = retrofitInterface.getAllUsers();
        listOfUsersCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                callback.onUsersLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onUsersLoaded(null);
            }
        });
    }

    @Override
    public void getAlbumsByUser(int userId, final LoadAlbumsByUserCallback callback) {
        Call<List<Album>> listOfUsersCall = retrofitInterface.getAlbumsByUser(userId);
        listOfUsersCall.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                callback.onAlbumsLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                callback.onAlbumsLoaded(null);
            }
        });
    }

    @Override
    public void getPostsByUser(int userId, final LoadPostsByUserCallback callback) {
        Call<List<Post>> listOfUsersCall = retrofitInterface.getPostsByUser(userId);
        listOfUsersCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                callback.onPostsLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                callback.onPostsLoaded(null);
            }
        });
    }

    @Override
    public void getPhotosByAlbum(int albumId, final LoadPhotosByAlbumCallback callback) {
        Call<List<Photo>> listOfUsersCall = retrofitInterface.getPhotosByAlbum(albumId);
        listOfUsersCall.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                callback.onImagesLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                callback.onImagesLoaded(null);
            }
        });
    }

    @Override
    public void postPost(String title, String body, int userId, final CreatePostCallback callback) {
        Call<ResponseBody> postCall = retrofitInterface.postPost(title, body, userId);
        postCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFail();
            }
        });
    }

    @Override
    public void deletePost(int postId, final DeletePostCallback callback) {
        Call<ResponseBody> deleteCall = retrofitInterface.deletePost(postId);
        deleteCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callback.onSuccess();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFail();
            }
        });
    }
}
