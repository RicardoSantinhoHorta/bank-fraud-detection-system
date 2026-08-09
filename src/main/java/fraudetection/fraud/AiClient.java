package fraudetection.fraud;

import fraudetection.fraud.dto.AiFraudPredictionRequestDTO;
import fraudetection.fraud.dto.AiFraudPredictionResponseDTO;
import fraudetection.transaction.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AiClient {

    private RestClient restClient;

    public AiClient() {
        this.restClient = RestClient.create(
                "http://localhost:5000"
        );
    }


    public double getRiskScore(Transaction transaction){

        AiFraudPredictionRequestDTO request = new AiFraudPredictionRequestDTO(
                transaction.getAmount().doubleValue(),
                transaction.getTransactionAmountLevel().name(),
                transaction.getTransactionAmountLevel().name(),
                transaction.getTransactionType().name()
        );

        AiFraudPredictionResponseDTO response = restClient
                .post()
                .uri("/predict")
                .body(request)
                .retrieve()
                .body(AiFraudPredictionResponseDTO.class);

        return response.riskScore();
    }
}
