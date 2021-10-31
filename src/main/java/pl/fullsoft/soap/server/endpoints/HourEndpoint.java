package pl.fullsoft.soap.server.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.fullsoft.soap_server.hours.GetHourRequest;
import pl.fullsoft.soap_server.hours.GetHourResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Endpoint
public class HourEndpoint {

    private static final String NAMESPACE_URI = "http://www.fullsoft.pl/soap-server/hours";

    public HourEndpoint() {
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getHourRequest")
    @ResponsePayload
    public GetHourResponse getHour(@RequestPayload GetHourRequest request) {
        GetHourResponse response = new GetHourResponse();
        if ("12".equals(request.getFormat())) {
            response.setHour(LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh")));
        } else {
            response.setHour(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH")));
        }

        return response;
    }
}
