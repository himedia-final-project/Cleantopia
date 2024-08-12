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
                            "    \"laundryTime\": (optimized laundry time in minutes),\n" +
                            "    \"laundryDetergentAmount\": (optimized detergent amount in mL),\n" +
                            "    \"laundryWaterAmount\": (optimized water amount in L),\n" +
                            "    \"laundryDryingTime\": (optimized drying time in minutes),\n" +
                            "    \"laundryDryCleaningTime\": (if 'laundryDryCleaningStatus' is 'Y', provide dry cleaning time in minutes; if 'N', set to 0).\n" +
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
            String result = chatCompletionChoice.getMessage().getContent();

            // 응답에서 laundryDryCleaningTime을 검증 및 수정
            if (laundryDryCleaningStatus.equals("Y") && result.contains("\"laundryDryCleaningTime\": 0")) {
                // laundryWeight에 따라 드라이클리닝 시간을 설정
                int dryCleaningTime;
                if (laundryWeight <= 3) {
                    dryCleaningTime = 15; // 3kg 이하일 때 15분
                } else if (laundryWeight <= 7) {
                    dryCleaningTime = 30; // 3kg 초과 ~ 7kg 이하일 때 30분
                } else if (laundryWeight <= 10) {
                    dryCleaningTime = 45; // 7kg 초과 ~ 10kg 이하일 때 45분
                } else {
                    dryCleaningTime = 60; // 10kg 초과일 때 60분
                }


                System.out.println("무게 몇이니?" + laundryWeight);

                // 결과 문자열에서 laundryDryCleaningTime을 설정된 시간으로 대체
                result = result.replace("\"laundryDryCleaningTime\": 0", "\"laundryDryCleaningTime\": " + dryCleaningTime);
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

}
