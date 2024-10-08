package page;

import java.util.List;

import okhttp3.ResponseBody;
import page.chat.entities.Chat;
import page.chat.entities.Contact;
import page.chat.entities.CreateChatUsername;
import page.chat.entities.Message;
import page.chat.entities.MessageRequest;
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
// talks to the server
public interface WebServiceAPI {
    @GET("Chats")
    Call<List<Contact>> getContacts(@Header("firebaseToken") String firebaseToken, @Header("Authorization") String authorizationHeader);
    // should just get a class that has a string inside
    @POST("Chats")
    Call<ResponseBody> createChat(@Header("Authorization") String authorizationHeader, @Body CreateChatUsername username);

    @POST("Chats/{id}/Messages")
    Call<ResponseBody> sendMessage(@Header("Authorization") String authorizationHeader, @Path("id") int id, @Body MessageRequest messageRequest);

    @GET("Chats/{id}/Messages")
    Call<List<Message>> getChatMessages(@Header("Authorization") String authorizationHeader, @Path("id") int id);

    @POST("Tokens")
    Call<ResponseBody> createToken(@Body UserPass userPass);

    @GET("Users/{username}")
    Call<User> getUser(@Header("firebaseToken") String firebaseToken, @Header("Authorization") String authorizationHeader, @Path("username") String username);

    @POST("Users")
    Call<ResponseBody> createUser(@Body UserPassName userPassName);
}