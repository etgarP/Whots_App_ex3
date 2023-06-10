package page.chat.api;

import java.util.List;

import page.chat.entities.Contact;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface WebServiceAPI {
    // EXAMPLES OF GET AND POST AND DELETE TO USE LATER
//    @GET("contacts")
//    Call<List<Contact>> getContacts();
//    @Post("contacts")
//    Call<Void> createContact(@Body Post post);
//    @Delete("post/{id}")
//    call<Void> deleteContact(@Path("id") int id);
    @GET("Chats")
    Call<List<Contact>> getContacts(@Header("Authorization") String authorizationHeader);
}
