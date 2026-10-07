package project.case1.network

import project.case1.models.ChatResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PythonApiService {

    @FormUrlEncoded
    @POST("chat")
    suspend fun sendMessage(
        @Field("message") message: String,
        @Field("session_id") sessionId: String
    ): ChatResponse
}
