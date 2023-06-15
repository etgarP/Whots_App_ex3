package page;

import java.util.List;

import okhttp3.ResponseBody;
import page.chat.entities.Chat;
import page.chat.entities.Contact;
import page.chat.entities.Message;
import page.chat.entities.User;
import page.sign_in.entities.UserPass;
import page.sign_in.entities.UserPassName;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {
    @GET("Chats")
    Call<List<Contact>> getContacts(@Header("Authorization") String authorizationHeader);
    // should just get a class that has a string inside
    @POST("Chats")
    Call<Void> createChat(@Header("Authorization") String authorizationHeader, @Path("username") String username);

    @GET("Chats/{id}")
    Call<Chat> getChat(@Header("Authorization") String authorizationHeader, @Path("id") int id);

    @DELETE("Chats/{id}")
    Call<Void> deleteChat(@Header("Authorization") String authorizationHeader, @Path("id") int id);

    @POST("Chats/{id}/Messages")
    Call<Void> sendMessage(@Header("Authorization") String authorizationHeader, @Path("id") int id, @Path("msg") String msg);

    @GET("Chats/{id}/Messages")
    Call<List<Message>> getChatMessages(@Header("Authorization") String authorizationHeader, @Path("id") int id);

    @POST("Tokens")
    Call<ResponseBody> createToken(@Body UserPass userPass);

    @GET("Users/{username}")
    Call<User> getUser(@Header("Authorization") String authorizationHeader, @Path("username") String username);

    @POST("Users")
    Call<ResponseBody> createUser(@Body UserPassName userPassName);
}