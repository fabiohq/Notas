HttpHeaders headers = new HttpHeaders();
headers.set("Authorization", "Bearer " + jwt);
headers.setContentType(MediaType.APPLICATION_JSON);

ObjectMapper mapper = new ObjectMapper();
String jsonRequest = mapper.writerWithDefaultPrettyPrinter()
                           .writeValueAsString(request);

System.out.println("===== REQUEST COMPLETO =====");
System.out.println("POST " + urlOneFcc + "/onboarding");
System.out.println("Headers: " + headers);
System.out.println("Body: " + jsonRequest);

HttpEntity<OneFccRequest> entity = new HttpEntity<>(request, headers);

response = restTemplate.exchange(
        urlOneFcc + "/onboarding",
        HttpMethod.POST,
        entity,
        OneFccResponse.class
);