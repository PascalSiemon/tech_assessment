package ch.psiemo.backend.api.natwest;

import ch.psiemo.backend.api.natwest.constants.NatWestEndpoints;
import ch.psiemo.backend.api.natwest.constants.NatWestFields;
import ch.psiemo.backend.api.natwest.constants.NatWestHeaders;
import ch.psiemo.backend.api.natwest.dto.*;
import ch.psiemo.backend.api.natwest.dto.account.AccountDetails;
import ch.psiemo.backend.api.natwest.dto.account.NatWestAccountResponse;
import ch.psiemo.backend.api.natwest.dto.balance.NatWestBalanceResponse;
import ch.psiemo.backend.api.natwest.dto.request.AccountRequest;
import ch.psiemo.backend.api.natwest.dto.request.PermissionWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

public class NatWestController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    private static final MappingJackson2HttpMessageConverter CONVERTER = new MappingJackson2HttpMessageConverter(OBJECT_MAPPER);

    public static AccessToken getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> tokenMap = new LinkedMultiValueMap();
        tokenMap.add(NatWestFields.GRANT_TYPE, "client_credentials");
        tokenMap.add(NatWestFields.CLIENT_ID, "_gJ9jV7WBWInp51K0WZhNqVEbSoKNOwHzyaU1bbDheY=");
        tokenMap.add(NatWestFields.CLIENT_SECRET, "pXHY0fWlmH7G8noTwuBYFc53DzuK6ctXijmVMQmM5hc=");
        tokenMap.add(NatWestFields.SCOPE, "accounts");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(tokenMap, headers);

        return restTemplate.postForObject(NatWestEndpoints.ACCESS_TOKEN, httpEntity, AccessToken.class);
    }

    public static AccountRequest getAccountRequest() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, CONVERTER);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(NatWestHeaders.AUTHORIZATION, "Bearer " + getAccessToken().getToken());
        headers.add(NatWestHeaders.FINANCIAL_ID, "0015800000jfwxXAAQ");

        AccountRequest accountRequest = AccountRequest.builder()
                .permissionWrapper(PermissionWrapper.builder()
                        .permissions(
                                Arrays.asList("ReadAccountsDetail",
                                        "ReadBalances",
                                        "ReadTransactionsCredits",
                                        "ReadTransactionsDebits",
                                        "ReadTransactionsDetail")
                        )
                        .build()
                )
                .risk(new Object())
                .build();

        HttpEntity<AccountRequest> httpEntity = new HttpEntity<>(accountRequest, headers);

        return restTemplate.postForObject(NatWestEndpoints.ACCOUNT_REQUEST, httpEntity, AccountRequest.class);
    }

    public static ConsentApproval approveConsent() {
        RestTemplate restTemplate = new RestTemplate();

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(NatWestEndpoints.AUTHORIZE)
                .queryParam(NatWestFields.CLIENT_ID, "{client_id}")
                .queryParam(NatWestFields.RESPONSE_TYPE, "{response_type}")
                .queryParam(NatWestFields.SCOPE, "{scope}")
                .queryParam(NatWestFields.REDIRECT_URI, "{redirect_uri}")
                .queryParam(NatWestFields.STATE, "{state}")
                .queryParam(NatWestFields.REQUEST, "{request}")
                .queryParam(NatWestFields.AUTHORIZATION_MODE, "{authorization_mode}")
                .queryParam(NatWestFields.AUTHORIZATION_USERNAME, "{authorization_username}")
                .encode()
                .toUriString();

        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put(NatWestFields.CLIENT_ID, "_gJ9jV7WBWInp51K0WZhNqVEbSoKNOwHzyaU1bbDheY=");
        queryParameters.put(NatWestFields.RESPONSE_TYPE, "code id_token");
        queryParameters.put(NatWestFields.SCOPE, "openid accounts");
        queryParameters.put(NatWestFields.REDIRECT_URI, "https://e2a982d9-36ec-48c7-9dcb-39e4b53d189d.example.org/redirect");
        queryParameters.put(NatWestFields.STATE, "ABC");
        queryParameters.put(NatWestFields.REQUEST, getAccountRequest().getPermissionWrapper().getAccountRequestId());
        queryParameters.put(NatWestFields.AUTHORIZATION_MODE, "AUTO_POSTMAN");
        queryParameters.put(NatWestFields.AUTHORIZATION_USERNAME, "123456789012@e2a982d9-36ec-48c7-9dcb-39e4b53d189d.example.org");

        return restTemplate.getForObject(urlTemplate, ConsentApproval.class, queryParameters);
    }

    public static ApiAccessToken getApiAccessToken() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> tokenMap = new LinkedMultiValueMap();
        tokenMap.add(NatWestFields.CLIENT_ID, "_gJ9jV7WBWInp51K0WZhNqVEbSoKNOwHzyaU1bbDheY=");
        tokenMap.add(NatWestFields.CLIENT_SECRET, "pXHY0fWlmH7G8noTwuBYFc53DzuK6ctXijmVMQmM5hc=");
        tokenMap.add(NatWestFields.REDIRECT_URI, "https://e2a982d9-36ec-48c7-9dcb-39e4b53d189d.example.org/redirect");
        tokenMap.add(NatWestFields.GRANT_TYPE, "authorization_code");
        tokenMap.add(NatWestFields.CODE,
                extractQueryParameters(approveConsent().getRedirectUri()).get(NatWestFields.CODE)
        );

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(tokenMap, headers);

        return restTemplate.postForObject(NatWestEndpoints.ACCESS_TOKEN, httpEntity, ApiAccessToken.class);
    }

    public static NatWestAccountResponse getAccounts() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add(NatWestHeaders.AUTHORIZATION, "Bearer " + getApiAccessToken().getAccessToken());
        headers.add(NatWestHeaders.FINANCIAL_ID, "0015800000jfwxXAAQ");

        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                NatWestEndpoints.ACCOUNTS,
                HttpMethod.GET,
                httpEntity,
                NatWestAccountResponse.class
        ).getBody();
    }

    public static NatWestBalanceResponse getBalance(String accountId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add(NatWestHeaders.AUTHORIZATION, "Bearer " + getApiAccessToken().getAccessToken());
        headers.add(NatWestHeaders.FINANCIAL_ID, "0015800000jfwxXAAQ");

        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        Map<String, String> parameters = new HashMap<>();
        parameters.put(NatWestFields.ACCOUNT_ID, accountId);

        return restTemplate.exchange(NatWestEndpoints.BALANCES, HttpMethod.GET, httpEntity, NatWestBalanceResponse.class, parameters).getBody();

    }

    public static List<NatWestBalanceResponse> getBalances() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add(NatWestHeaders.AUTHORIZATION, "Bearer " + getApiAccessToken().getAccessToken());
        headers.add(NatWestHeaders.FINANCIAL_ID, "0015800000jfwxXAAQ");

        List<String> accountIds = getAccounts().getAccountWrapper().getAccounts()
                .stream()
                .map(AccountDetails::getAccountId)
                .toList();

        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        List<NatWestBalanceResponse> balances = new ArrayList<>();
        for (String accountId : accountIds) {
            Map<String, String> parameters = new HashMap<>();
            parameters.put(NatWestFields.ACCOUNT_ID, accountId);
            balances.add(restTemplate.exchange(
                    NatWestEndpoints.BALANCES,
                    HttpMethod.GET,
                    httpEntity,
                    NatWestBalanceResponse.class,
                    parameters
            ).getBody());
        }

        return balances;
    }

    private static Map<String, String> extractQueryParameters(String uri) {
        Map<String, String> queryParameters = new HashMap<>();
        for (String queryParameter : uri.substring(uri.lastIndexOf('#') + 1).split("&")) {
            String[] keyValuePair = queryParameter.split("=");
            queryParameters.put(keyValuePair[0], keyValuePair[1]);
        }
        return queryParameters;
    }
}
