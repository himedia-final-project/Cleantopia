package aircleanprojectback.restapi.water.service;

import aircleanprojectback.restapi.water.dao.WaterRepository;
import aircleanprojectback.restapi.water.dto.Row;
import aircleanprojectback.restapi.water.dto.WPOSInformationTimeDTO;
import aircleanprojectback.restapi.water.entity.WaterCondition;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;


@EnableScheduling
@Service
public class ScheduleTaskService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTaskService.class);
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;
    private final WaterRepository repository;

    @Autowired
    public ScheduleTaskService(ObjectMapper objectMapper, WaterRepository repository,ModelMapper modelMapper){
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
        this.repository=repository;
    }

    @Scheduled(cron = "0 0 9 * * *")
    public void fetchData() {
        logger.info("Fetching data...");

        String key = "534a674e667363693131304c4771684c";
        String baseUrl = "http://openapi.seoul.go.kr:8088";
        String endpoint = "/WPOSInformationTime/1/5/";
        String requestURL = baseUrl + "/" + key + "/json" + endpoint;

        HttpURLConnection conn = null;
        BufferedReader rd = null;

        try {
            URL url = new URL(requestURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json"); // 응답 타입에 맞게 설정

            int responseCode = conn.getResponseCode();
            logger.info("Response code: " + responseCode);

            InputStreamReader inputStreamReader = new InputStreamReader(
                    responseCode >= 200 && responseCode <= 300 ? conn.getInputStream() : conn.getErrorStream()
            );
            rd = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            String jsonResponse = sb.toString();
            logger.info("Response body: " + sb.toString());

            WPOSInformationTimeDTO wposInfo = objectMapper.readValue(jsonResponse, WPOSInformationTimeDTO.class);
            logger.info("Parsed data: " + wposInfo);

            List<Row> rows = wposInfo.getData().getRow();

            System.out.println("rows = " + rows);

            List<WaterCondition> waterConditions = rows.stream().map(row-> modelMapper.map(row,WaterCondition.class)).collect(Collectors.toList());

            System.out.println("waterConditions = " + waterConditions);

            repository.saveAll(waterConditions);




        } catch (IOException e) {
            logger.error("Error fetching data: ", e);
        } finally {
            try {
                if (rd != null) rd.close();
                if (conn != null) conn.disconnect();
            } catch (IOException e) {
                logger.error("Error closing resources: ", e);
            }
        }
    }



}
