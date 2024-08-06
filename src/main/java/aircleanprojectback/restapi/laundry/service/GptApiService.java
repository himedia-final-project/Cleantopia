package aircleanprojectback.restapi.laundry.service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class GptApiService {

    @Value("${openai.api-key}")
    private String openAiApiKey;

    public String getOptimizedLaundryData(int laundryCode, int laundryWeight, String laundryFabricType, String laundryDryCleaningStatus, int laundryDirtyLevel) {
        try {
            // OpenAiService 인스턴스 생성
            OpenAiService service = new OpenAiService(openAiApiKey, Duration.ofSeconds(10));

            // 요청 메시지 구성
            String input = String.format(
                    "Given the following laundry details, please provide the following information in JSON format: {\n" +
                            "    \"laundryCode\": %d,\n" +
                            "    \"laundryWeight\": %d,\n" +
                            "    \"laundryFabricType\": \"%s\",\n" +
                            "    \"laundryDryCleaningStatus\": \"%s\",\n" +
                            "    \"laundryDirtyLevel\": %d\n" +
                            "}. \n" +
                            "The required fields are: \n" +
                            "    \"laundryCode\": (return the original laundryCode as provided),\n" +
                            "    \"laundryTime\": (optimized laundry time),\n" +
                            "    \"laundryDetergentAmount\": (optimized detergent amount),\n" +
                            "    \"laundryWaterAmount\": (optimized water amount),\n" +
                            "    \"laundryDryingTime\": (optimized drying time),\n" +
                            "    \"laundryDryCleaningTime\": (if 'Y', provide dry cleaning time; if 'N', set to 0).\n" +
                            "Return the result as a JSON object with these fields.",
                    laundryCode, laundryWeight, laundryFabricType, laundryDryCleaningStatus, laundryDirtyLevel
            );

            // ChatMessage 객체 생성
            ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), input);
            List<ChatMessage> messages = new ArrayList<>();
            messages.add(userMessage);

            // ChatCompletionRequest 생성
            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                    .model("gpt-3.5-turbo")
                    .messages(messages)
                    .maxTokens(300)
                    .build();

            // API 호출 및 응답 처리
            ChatCompletionChoice chatCompletionChoice = service.createChatCompletion(chatCompletionRequest).getChoices().get(0);
            return chatCompletionChoice.getMessage().getContent();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
